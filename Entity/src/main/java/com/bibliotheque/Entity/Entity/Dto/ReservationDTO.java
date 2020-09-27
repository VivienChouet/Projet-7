package com.bibliotheque.Entity.Entity.Dto;

import com.bibliotheque.Entity.Entity.Exemplaire;
import com.bibliotheque.Entity.Entity.User;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class ReservationDTO {

    public int id;
    public Date date_debut;
    public Date date_fin;

    @ManyToOne
    private User users;
    @ManyToOne
    private Exemplaire exemplaire;
}

