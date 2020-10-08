package com.bibliotheque.API.Entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "book")
@Data
public class Book {

   @Id
    public int id;
    public String title;
    public String author;
    public Date publication;
    public String resume;

 }


