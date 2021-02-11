package com.bibliotheque.API.Entity.Dto;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class UserDTO {

    private int id;
    public String name ;
    public String email;
    public String password;
    private String token;
    public boolean admin;

}
