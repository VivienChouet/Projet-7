package com.bibliotheque.Entity.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
@Data(staticConstructor = "of")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public Date date_debut;
    public Date date_fin;

    @ManyToOne
    private User users;
    @ManyToOne
    private Exemplaire exemplaire;
}
