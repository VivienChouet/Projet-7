package com.bibliotheque.API.Entity.Dto;

import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {

    public int id;
    public Date date_debut;
    public Date date_fin;

    private UserDTO user;

    private ExemplaireDTO exemplaire;
}

