package com.bibliotheque.Web.service;
import com.bibliotheque.Web.Entity.Dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class UserService {

    HttpClient httpClient = HttpClient.newBuilder().build();


    public void connexion(UserDTO userDTO) throws JsonProcessingException {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/user/login?username="+userDTO.name+"&pwd="+userDTO.password)).headers("Content-Type" , "application/json").POST(HttpRequest.BodyPublishers.ofString("")).build();

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


    }

    public UserDTO idUser(int id) throws JsonProcessingException {


        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/user/"+id)).headers("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpZCIsInN1YiI6IlRlc3QwMSIsImlhdCI6MTYwNjkyNzc3NSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTYwNjkyNzgzNX0.Jzuz6-zFbTVueyLJpHT0SNgeNzRXKMRBpSJStXWX89U").GET().build();

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
        var value = mapper.readValue((String) json, UserDTO.class);


        return value;
    }


}
