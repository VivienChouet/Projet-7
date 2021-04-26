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

    /**
     *
     * @param userDTO
     * @throws JsonProcessingException
     */
    public void connexion(UserDTO userDTO) throws JsonProcessingException {
        logger.info("connexion de : " + userDTO.name);
        String json = (String) operateurDiamant.jsonConvert(userDTO);
        HttpResponse response = operateurDiamant.post("http://localhost:8080/user/login", json);

        UserDTO userDTO1 = (UserDTO) operateurDiamant.singleObject(response, UserDTO.class);
        token = userDTO1.getToken();
        logger.info(token);
    }

    /**
     *
     * @param id
     * @return UserDTO
     * @throws JsonProcessingException
     */
    public UserDTO idUser(int id) throws JsonProcessingException {
        HttpResponse response = operateurDiamant.RequestSecure("http://localhost:8080/user/" + id, token);
        UserDTO userDTO = (UserDTO) operateurDiamant.singleObject(response, UserDTO.class);

        return userDTO;
    }

    /**
     *
     * @return UserDTO
     * @throws JsonProcessingException
     */
    public UserDTO connectedUser() throws JsonProcessingException {
        HttpResponse response = operateurDiamant.RequestSecure("http://localhost:8080/user/token", token);
        UserDTO user = (UserDTO) operateurDiamant.singleObject(response, UserDTO.class);
        return user;
    }

    /**
     *
     * @return List<UserDTO>
     * @throws JsonProcessingException
     */
    public List<UserDTO> listUser() throws JsonProcessingException {
        HttpResponse response = operateurDiamant.RequestSecure("http://localhost:8080/user/", token);
        List<UserDTO> userDTOS = operateurDiamant.listObject(response, UserDTO.class);
        return userDTOS;
    }

    /**
     *
     * @param userDTO
     * @throws JsonProcessingException
     */
    public void newUser(UserDTO userDTO) throws JsonProcessingException {
        String json = (String) operateurDiamant.jsonConvert(userDTO);
        HttpResponse response = operateurDiamant.post("http://localhost:8080/user/", json);
    }

    /**
     *
     * @return Boolean userDTO.admin
     * @throws JsonProcessingException
     */
    public boolean admin() throws JsonProcessingException {
        if (token == null) {
            return false;
        }
        UserDTO userDTO = connectedUser();
        return userDTO.admin;
    }

    /**
     *
     * @return Boolean connected
     */
    public boolean connected() {
        boolean connected;
        connected = token != null;
        return connected;
    }

    /**
     *
     */
    public void logout() {
        token = null;
        admin = false;
    }

    public boolean token() {
        return token != null;
    }


}
