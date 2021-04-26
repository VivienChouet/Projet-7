package com.bibliotheque.Web.Entity.Dto;

import lombok.Data;

@Data
public class ExemplaireNumberDTO {

    public int id;
    public String edition;
    public BookDTO book;
    public boolean available;
    public int number;
}
