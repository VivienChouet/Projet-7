package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.ReservationDTO;
import com.bibliotheque.batch.Utility.OperateurDiamant;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Writer implements ItemWriter<ReservationDTO> {

    @Autowired
    OperateurDiamant operateurDiamant;

    public Writer(){

    }
    HttpClient httpClient = HttpClient.newBuilder().build();

    @Override
    public void write(List<? extends ReservationDTO> reservationDTOS) throws Exception {
        System.out.println("size writer : " + reservationDTOS.size());

            HttpRequest requestPost = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/batch/"+reservationDTOS.get(0).id)).setHeader("Content-Type" , "application/json").POST(HttpRequest.BodyPublishers.ofString("json")).build();
            HttpResponse response = null;

            try {
                response = httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());
                System.out.println("Body    : " + reservationDTOS );
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }


}
