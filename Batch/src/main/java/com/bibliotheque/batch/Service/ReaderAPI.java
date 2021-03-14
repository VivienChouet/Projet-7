package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.ReservationDTO;
import com.bibliotheque.batch.Utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

public class ReaderAPI {

    @Autowired
    OperateurDiamant operateurDiamant;

    public ReaderAPI() {
    }

    public static HttpResponse httpResponse() {

        HttpClient httpClient = HttpClient.newBuilder().build();

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/batch/")).GET().build();
        HttpResponse response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static List<ReservationDTO> reservationDTOS(HttpResponse response) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        var mapCollectionType = mapper.getTypeFactory().constructCollectionType(List.class, ReservationDTO.class);
        var json = response.body();
        List<ReservationDTO> value = mapper.readValue(String.valueOf(json), mapCollectionType);
        return value;
    }
}
