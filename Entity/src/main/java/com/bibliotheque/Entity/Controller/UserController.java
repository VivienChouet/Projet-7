package com.bibliotheque.Entity.Controller;

import com.bibliotheque.Entity.Entity.Dto.UserDTO;
import com.bibliotheque.Entity.Entity.Mapper.UserMapper;
import com.bibliotheque.Entity.Entity.User;
import com.bibliotheque.Entity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


    @GetMapping("/")
    public List<User> listUser() {
        List<User> users = this.userService.findAll();
        return users;

    }

    @GetMapping("/{id}")
    public User userId(@PathVariable int id) {
        User user = this.userService.findById(id);
        return user;
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> newUser (@RequestBody UserDTO userDTO){
    System.out.println("User => " + userMapper.toEntity(userDTO));
    userService.save(userMapper.toEntity(userDTO));
    return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}



