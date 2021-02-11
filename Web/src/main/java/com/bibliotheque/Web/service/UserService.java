package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.UserDTO;
import com.bibliotheque.Web.utility.LoggingController;
import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;

@Service
public class UserService {

    @Autowired
        OperateurDiamant operateurDiamant;
   Logger logger = LoggerFactory.getLogger(LoggingController.class);

    String token;

    boolean admin = false;

    public void connexion(UserDTO userDTO) throws JsonProcessingException {
        logger.info("connexion de : " + userDTO.name );
        String json = (String) operateurDiamant.jsonConvert(userDTO);
        HttpResponse response = operateurDiamant.post("http://localhost:8080/user/login", json);

       UserDTO userDTO1 = (UserDTO) operateurDiamant.singleObject(response,UserDTO.class);
       token = userDTO1.getToken();
       logger.info(token);
    }

    public UserDTO idUser(int id) throws JsonProcessingException {
        HttpResponse response = operateurDiamant.RequestSecure("http://localhost:8080/user/" + id, token);
        UserDTO userDTO = (UserDTO) operateurDiamant.singleObject(response, UserDTO.class);

        return userDTO;
    }

    public UserDTO connectedUser() throws JsonProcessingException {
        HttpResponse response = operateurDiamant.RequestSecure("http://localhost:8080/user/token", token);
        UserDTO user = (UserDTO) operateurDiamant.singleObject(response, UserDTO.class);
        return user;
    }

    public List<UserDTO> listUser() throws JsonProcessingException {
        HttpResponse response = operateurDiamant.RequestSecure("http://localhost:8080/user/", token);
        List<UserDTO> userDTOS = operateurDiamant.listObject(response, UserDTO.class);
        return userDTOS;
    }

    public void newUser (UserDTO userDTO) throws JsonProcessingException {
        String json = (String) operateurDiamant.jsonConvert(userDTO);
        HttpResponse response = operateurDiamant.post("http://localhost:8080/user/", json);
    }

    public boolean admin () throws JsonProcessingException {
        if(token == null){return false;}
        UserDTO userDTO = connectedUser();
        if (userDTO.admin){
        return true;}
        return false;
    }

    public boolean connected()  {
        boolean connected;
        if(token != null){ connected = true; }
        else {connected = false;}
return connected;
    }

    public void logout(){
        token = null;
        admin = false;
    }

    public boolean token(){
        if (token != null) {
            return true;
        }
        return false;
    }



}
