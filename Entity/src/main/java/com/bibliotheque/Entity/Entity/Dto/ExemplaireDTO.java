package com.bibliotheque.Entity.Entity.Dto;

import com.bibliotheque.Entity.Entity.Book;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class ExemplaireDTO {

    public int id;
    public String edition;

    @ManyToOne
    private Book book;

}
