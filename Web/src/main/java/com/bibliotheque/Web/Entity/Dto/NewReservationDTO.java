package com.bibliotheque.Web.Entity.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class NewReservationDTO {

    public int id;
    public Date date_debut;
    public Date date_fin;
    private int iduser;
    private int idexemplaire;
}

