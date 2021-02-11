package com.bibliotheque.API.Controller;

import com.bibliotheque.API.Entity.Dto.UserDTO;
import com.bibliotheque.API.Entity.Mapper.UserMapper;
import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public User login(@RequestBody UserDTO userDTO) {

        if (userService.loginUser(userDTO.name, userDTO.password)) {
            String token = userService.createJWT(userDTO.name, 60000);
            User user = new User();
            user.setName(userDTO.name);
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
            String username = userService.findUsernameByToken(token);
            User user = userService.findByUsername(username);
            return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}






