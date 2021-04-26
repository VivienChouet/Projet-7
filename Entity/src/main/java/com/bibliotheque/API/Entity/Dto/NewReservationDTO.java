package com.bibliotheque.API.Entity.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class NewReservationDTO {

    public int id;
    public Date date_debut;
    public Date date_fin;

    public int iduser;
    public int idexemplaire;
}

