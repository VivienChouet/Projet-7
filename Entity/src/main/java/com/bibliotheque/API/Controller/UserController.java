package com.bibliotheque.API.Controller;

import com.bibliotheque.API.Entity.Dto.UserDTO;
import com.bibliotheque.API.Entity.Mapper.UserMapper;
import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")

public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listUser() {
        List<User> users = this.userService.findAll();
        return new ResponseEntity<>(userMapper.toDto(users), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> userId(@PathVariable int id) {
        User user = this.userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> newUser(@RequestBody UserDTO userDTO) {
        userService.save(userMapper.toEntity(userDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        userService.save((userMapper.toEntity(userDTO)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public User login(@RequestParam String username, @RequestParam String pwd) {

        if (userService.loginUser(username, pwd)) {
            String token = createJWT(username, 60000);
            User user = new User();
            user.setName(username);
            user.setToken(token);
            System.out.println(user.getToken());
            return user;

        }
        return null;
    }


    @GetMapping("/token")
    public ResponseEntity<UserDTO> userToken(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        if (token != null) {
            String username = getIdJWTToken(token);
            User user = userService.findByUsername(username);
            return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return "Bearer " + builder.compact();
    }


    private String getIdJWTToken(String token) {
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
}






