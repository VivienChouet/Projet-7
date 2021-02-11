package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.NewReservationDTO;
import com.bibliotheque.Web.Entity.Dto.ReservationDTO;
import com.bibliotheque.Web.Entity.Dto.UserDTO;
import com.bibliotheque.Web.utility.LoggingController;
import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    UserService userService;

    @Autowired
    OperateurDiamant operateurDiamant;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    public void newReservation(int idExemplaire) throws JsonProcessingException {
        NewReservationDTO newreservationDTO = new NewReservationDTO();
        UserDTO userDTO = userService.connectedUser();
        int idUser = userDTO.getId();
        newreservationDTO.setIduser(idUser);
        newreservationDTO.setIdexemplaire(idExemplaire);
        newreservationDTO.setDate_debut(new Date());
        newreservationDTO.setDate_fin(new Date());

        String json = (String) operateurDiamant.jsonConvert(newreservationDTO);
        operateurDiamant.post("http://localhost:8080/reservation/", json);
    }

    public List<ReservationDTO> reservationByUser() throws JsonProcessingException {
        System.out.println("TOKEN = " + userService.token);
        HttpResponse response = operateurDiamant.RequestSecure("http://localhost:8080/reservation/myreservation", userService.token);
        List<ReservationDTO> reservations = operateurDiamant.listObject(response, ReservationDTO.class);
        return reservations;
    }

    public ReservationDTO findById(int id) throws JsonProcessingException {
        HttpResponse response = operateurDiamant.Request("http://localhost:8080/reservation/" + id);
        ReservationDTO reservation = (ReservationDTO) operateurDiamant.singleObject(response, ReservationDTO.class);
        return reservation;
    }

    public void extension(int id){
      operateurDiamant.post("http://localhost:8080/reservation/extension/" + id,"vide");
    }

    public void endedReservation(int id){
       operateurDiamant.post("http://localhost:8080/reservation/return/" + id, "vide");
    }

    public List<ReservationDTO> listReservation() throws JsonProcessingException {
        HttpResponse response = operateurDiamant.Request("http://localhost:8080/reservation/");
        List<ReservationDTO> reservationDTOS = operateurDiamant.listObject(response, ReservationDTO.class);

        return reservationDTOS;
    }

}
