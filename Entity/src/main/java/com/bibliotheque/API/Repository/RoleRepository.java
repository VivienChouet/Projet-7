package com.bibliotheque.API.Repository;

import com.bibliotheque.API.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Object findByName(String role);
}
