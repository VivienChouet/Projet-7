package com.bibliotheque.API.Controller;

import com.bibliotheque.API.Entity.Dto.UserDTO;
import com.bibliotheque.API.Entity.Mapper.UserMapper;
import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Service.UserService;
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
}



