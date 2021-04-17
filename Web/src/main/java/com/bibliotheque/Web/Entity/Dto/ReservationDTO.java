package com.bibliotheque.Web.Entity.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {

    public int id;
    public Date date_debut;
    public Date date_fin;
    private boolean ended;
    private boolean extension;
    private boolean batch;

    private UserDTO user;

    private ExemplaireDTO exemplaire;
}

