package com.bibliotheque.Entity.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data(staticConstructor = "of")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public String name ;
    public String email;
    public String password;


}

