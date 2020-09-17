package com.bibliotheque.Entity.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "exemplaire")
@Data (staticConstructor = "of")
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String edition;

    @ManyToOne
    private Book book;

}
