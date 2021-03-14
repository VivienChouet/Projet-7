package com.bibliotheque.Web.Entity.Dto;


import lombok.Data;

import java.util.Date;


@Data
public class BookDTO {

    private int id;
    private String title;
    private String author;
    private String publication;
    private String resume;

}
