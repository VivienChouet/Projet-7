package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.BookDTO;
import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;

@Service
public class BookService {


@Autowired
OperateurDiamant operateurDiamant;

    /**
     * List all book
     * @return List<BookDTO>
     * @throws JsonProcessingException
     */
    public List<BookDTO> listBook() throws JsonProcessingException {
    HttpResponse response = operateurDiamant.Request( "http://localhost:8080/book/");
    List<BookDTO> bookDTO = operateurDiamant.listObject(response,BookDTO.class);
  return bookDTO;
    }

    /**
     * Find BookDTO By Id
     * @param id
     * @return BookDTO
     * @throws JsonProcessingException
     */
    public BookDTO findById(int id) throws JsonProcessingException {
        HttpResponse response = operateurDiamant.Request("http://localhost:8080/book/" +id );
        BookDTO bookDTO = (com.bibliotheque.Web.Entity.Dto.BookDTO) operateurDiamant.singleObject(response,BookDTO.class);
      return bookDTO;

    }

    /**
     * save
     * @param bookDTO
     * @throws JsonProcessingException
     */
    public void newBook(BookDTO bookDTO) throws JsonProcessingException {
        String json = (String) operateurDiamant.jsonConvert(bookDTO);
        operateurDiamant.post("http://localhost:8080/book/", json);
    }

    /**
     * Find BookDTO by Title
     * @param title
     * @return BookDTO
     * @throws JsonProcessingException
     */
    public BookDTO findByTitle(String title) throws JsonProcessingException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(title);
        String json = (String) operateurDiamant.jsonConvert(bookDTO);
        HttpResponse response = operateurDiamant.post("http://localhost:8080/book/search", json);
        BookDTO bookDTO1 = (BookDTO) operateurDiamant.singleObject(response, BookDTO.class);
        return bookDTO1;
    }
}
