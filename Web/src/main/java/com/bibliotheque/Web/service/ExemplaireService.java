package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.BookDTO;
import com.bibliotheque.Web.Entity.Dto.ExemplaireDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ExemplaireService {


    HttpClient httpClient = HttpClient.newBuilder().build();

    public List<ExemplaireDTO> listExemplaireByIdBook(int id) throws JsonProcessingException {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/exemplaire/book/"+id)).GET().build();

        HttpResponse response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status  : " + response.statusCode());
            System.out.println("Headers : " + response.headers());
            System.out.println("Body    : " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        var mapper = new ObjectMapper();
        var mapCollectionType = mapper.getTypeFactory().constructCollectionType(List.class, ExemplaireDTO.class);
        var json = response.body();
        List<ExemplaireDTO> value = mapper.readValue(String.valueOf(json), mapCollectionType);

        return value;

    }

    public void save(ExemplaireDTO exemplaireDTO, int id) {


    }
}
