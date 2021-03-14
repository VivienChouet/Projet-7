package com.bibliotheque.batch.DTO;

import lombok.Data;

@Data
public class UserDTO {

    private int id;
    public String name ;
    public String email;
    public String password;
    private String token;
    public boolean admin;

}
