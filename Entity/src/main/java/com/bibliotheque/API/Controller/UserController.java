package com.bibliotheque.API.Controller;

import com.bibliotheque.API.Entity.Dto.UserDTO;
import com.bibliotheque.API.Entity.Mapper.UserMapper;
import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity <List<UserDTO>> listUser() {
        List<User> users = this.userService.findAll();
        return new ResponseEntity<>(userMapper.toDto(users), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> userId(@PathVariable int id) {
        User user = this.userService.findById(id);
        if (user == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> newUser (@RequestBody UserDTO userDTO){
       userService.save(userMapper.toEntity(userDTO));
    return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<UserDTO> updateUser (@RequestBody UserDTO userDTO){
        userService.save((userMapper.toEntity(userDTO)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser (@PathVariable int id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String pwd) {

        if (userService.loginUser(username,pwd)==true) {
            String token = getJWTToken(username);
            User user = new User();
            user.setName(username);
            user.setToken(token);
            return user.getToken();
        }
        return null;
    }

    @GetMapping("/token")
    public ResponseEntity<UserDTO> userToken (@RequestParam String token){

        User user = this.userService.findUserByToken(token);
        if (user == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }


    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}



