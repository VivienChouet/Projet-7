package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.NewReservationDTO;
import com.bibliotheque.Web.Entity.Dto.ReservationDTO;
import com.bibliotheque.Web.Entity.Dto.UserDTO;
import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
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
        HttpResponse response = operateurDiamant.RequestSecure("http://localhost:8080/myreservation/", userService.token);
        List<ReservationDTO> reservations = operateurDiamant.listObject(response,ReservationDTO.class);
        return reservations;
    }
}
