package com.bibliotheque.API.Service;

import com.bibliotheque.API.Repository.RoleRepository;
import com.bibliotheque.API.Repository.UserRepository;
import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

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
}
