package com.bibliotheque.API.Entity.Dto;


import com.bibliotheque.API.Entity.User;

import javax.persistence.ManyToMany;
import java.util.List;

public class RoleDTO {

    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> user;
}
