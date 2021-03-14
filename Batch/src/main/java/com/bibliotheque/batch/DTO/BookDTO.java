package com.bibliotheque.batch.DTO;

import lombok.Data;

import java.util.Date;

    @Data
    public class BookDTO {

        private int id;
        private String title;
        private String author;
        private Date publication;
        private String resume;
    }


