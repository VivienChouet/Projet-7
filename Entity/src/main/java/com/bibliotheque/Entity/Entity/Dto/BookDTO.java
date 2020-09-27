package com.bibliotheque.Entity.Entity.Dto;


import lombok.Data;

import java.util.Date;

@Data
public class BookDTO {

    private int id;
    public String title;
    public String author;
    public Date publication;
    public String resume;
}
