package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Repository.UserRepository;
import com.bibliotheque.API.Utility.LoggingController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        logger.info("List User");
        List<User> users = this.userRepository.findAll();
        return users;
    }

    public User findById(int id) {
        logger.info("find UserId = " + id);
        User user = this.userRepository.findById(id).get();
        return user;
    }

    public void save(User user) {
        logger.info("new user = " + user.name );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void delete(int id) {
        logger.info("delete User = " + id);
        userRepository.delete(findById(id));
    }

    public Boolean loginUser(String user, String password){
        logger.info("check login in progress");
        User user1 = userRepository.findByName(user);
        if (passwordEncoder.matches(password, user1.getPassword()))
        {
            logger.info("Check login success");
            return true;
        }
        logger.info("check login failed");
        return false;
    }



    public static String createJWT(String username, long ttlMillis) {

        String SECRET_KEY = "mySecretKey";

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId("id")
                .setSubject(username)
                .setIssuedAt(now)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
       /* if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }*/

        //Builds the JWT and serializes it to a compact, URL-safe string
        return "Bearer " + builder.compact();
    }


    public String findUsernameByToken (String token) {
        String jwtToken = token.replace("Bearer ", "");
        String username = decodeJWT(jwtToken).getSubject();

        return username;

    }

    public static Claims decodeJWT(String jwt) {
        String SECRET_KEY = "mySecretKey";
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;

    }


    public User findByUsername(String username) {
        logger.info("Find User By Username = " + username );
        User user = userRepository.findByName(username);
        return user;
    }
}
