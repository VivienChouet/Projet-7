package com.bibliotheque.Entity.Entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "book")
@Data
public class Book {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    public String title;
    public String author;
    public Date publication;
    public String resume;




}
