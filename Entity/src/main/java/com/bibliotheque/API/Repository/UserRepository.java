package com.bibliotheque.API.Repository;

import com.bibliotheque.API.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String user);

    User findByToken(String token);


}
