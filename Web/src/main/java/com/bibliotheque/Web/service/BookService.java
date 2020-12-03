package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.BookDTO;
import com.bibliotheque.Web.utility.KryoInit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class BookService {



    HttpClient httpClient = HttpClient.newBuilder().build();

    public List<BookDTO> listBook() throws JsonProcessingException {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/book/")).GET().build();

        HttpResponse response = null;
        BookDTO bookDTO = new BookDTO();

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status  : " + response.statusCode());
            System.out.println("Headers : " + response.headers());
            System.out.println("Body    : " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        var mapper = new ObjectMapper();
        var mapCollectionType = mapper.getTypeFactory().constructCollectionType(List.class, BookDTO.class);
        var json = response.body();
        List<BookDTO> value = mapper.readValue(String.valueOf(json), mapCollectionType);
        return value;
    }

    public BookDTO findById(int id) throws JsonProcessingException {


        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/book/"+id)).GET().build();

        HttpResponse response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status  : " + response.statusCode());
            System.out.println("Headers : " + response.headers());
            System.out.println("Body    : " + response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        var json = response.body();
        var value = mapper.readValue((String) json, BookDTO.class);


        return value;
    }
public void newBook(BookDTO bookDTO) throws JsonProcessingException {

        var mapper = new ObjectMapper();
           var json = mapper.writeValueAsString(bookDTO);

        HttpRequest requestPost = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/book/")).setHeader("Content-Type" , "application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();


        try {
            httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());
            System.out.println("Body    : " + json );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }


}
