package com.bibliotheque.Web.utility;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

@Service
public class Generic {


    HttpClient httpClient = HttpClient.newBuilder().build();

    public HttpResponse Request(String url){

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status  : " + response.statusCode());
            System.out.println("Headers : " + response.headers());
            System.out.println("Body    : " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    public void post (String url){

    }
}
