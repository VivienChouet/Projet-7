package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.BookDTO;
import com.bibliotheque.Web.utility.Generic;
import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class BookService {
@Autowired
Generic generic;

@Autowired
OperateurDiamant operateurDiamant;

    public List<BookDTO> listBook() throws JsonProcessingException {

    HttpResponse response = operateurDiamant.Request( "http://localhost:8080/book/");
    List<BookDTO> bookDTO = operateurDiamant.listObject(response,BookDTO.class);
  return bookDTO;
    }

    public BookDTO findById(int id) throws JsonProcessingException {

        HttpResponse response = operateurDiamant.Request("http://localhost:8080/book/" +id );
        BookDTO bookDTO = (com.bibliotheque.Web.Entity.Dto.BookDTO) operateurDiamant.singleObject(response,BookDTO.class);

      return bookDTO;

    }

public void newBook(BookDTO bookDTO) throws JsonProcessingException {

         String json = (String) operateurDiamant.jsonConvert(bookDTO);
         operateurDiamant.post("http://localhost:8080/book/", json);

    }


}
