package com.bibliotheque.API.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
        @Column(nullable = false, unique = true)

        private String name;

        @ManyToMany(mappedBy = "roles")
        private List<User> user;
}
