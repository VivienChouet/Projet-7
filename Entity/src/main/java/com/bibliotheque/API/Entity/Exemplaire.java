package com.bibliotheque.API.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "exemplaire")
@Data
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String edition;

    @ManyToOne
    private Book book;

}
