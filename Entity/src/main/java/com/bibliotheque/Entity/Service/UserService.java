package com.bibliotheque.Entity.Service;

import com.bibliotheque.Entity.Entity.User;
import com.bibliotheque.Entity.Repository.UserRepository;
import com.bibliotheque.Entity.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    UserRepository userRepository;

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
        userRepository.save(user);
    }
}
