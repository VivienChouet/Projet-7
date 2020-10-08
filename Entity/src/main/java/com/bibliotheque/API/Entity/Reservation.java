package com.bibliotheque.API.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public Date date_debut;
    public Date date_fin;

    @ManyToOne
    private User users;
    @ManyToOne
    private Exemplaire exemplaire;
}
