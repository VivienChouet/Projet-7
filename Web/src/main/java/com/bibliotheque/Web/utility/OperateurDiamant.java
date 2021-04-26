package com.bibliotheque.Web.utility;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class OperateurDiamant<T> {


    /**
        Mapper reader
     */

    /**
     * Method to convert response to single Object
     * @param response
     * @param classType
     * @return classType value
     * @throws JsonProcessingException
     */
    public T singleObject(HttpResponse response, Class<T> classType) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        var json = response.body();
        T value = mapper.readValue((String) json, classType);
        return value;
    }

    /**
     * Method to convert response to List of Object
     * @param response
     * @param classType
     * @return List<T>
     * @throws JsonProcessingException
     */
    public List<T> listObject(HttpResponse response, Class<T> classType) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        var mapCollectionType = mapper.getTypeFactory().constructCollectionType(List.class, classType);
        var json = response.body();
        List<T> value = mapper.readValue(String.valueOf(json), mapCollectionType);
        return value;
    }

    /**
     * Method to convert an object to json
     * @param object
     * @return json
     * @throws JsonProcessingException
     */
    public Object jsonConvert(Object object) throws JsonProcessingException {

        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(object);

        return json;
    }

    /**
     * HttpRequest
     */

    HttpClient httpClient = HttpClient.newBuilder().build();

    /**
     * Method GET
     * @param url
     * @return
     */

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

    /**
     * Method POST
     * @param url
     * @param json
     * @return
     */
    public HttpResponse post (String url, String json){

        HttpRequest requestPost = HttpRequest.newBuilder().uri(URI.create(url)).setHeader("Content-Type" , "application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();
        HttpResponse response = null;

        try {
            response = httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());
            System.out.println("Body    : " + json );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
 return response;

    }

    /**
     * Method GET with Token
     * @param url
     * @param token
     * @return
     */

    public HttpResponse RequestSecure (String url, String token){

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create(url)).setHeader("Authorization",token).GET().build();

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


}
