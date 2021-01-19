package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Dto.NewReservationDTO;
import com.bibliotheque.API.Entity.Reservation;
import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Repository.ExemplaireRepository;
import com.bibliotheque.API.Repository.ReservationRepository;
import com.bibliotheque.API.Repository.UserRepository;
import com.bibliotheque.API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExemplaireRepository exemplaireRepository;
    @Autowired
    UserService userService;


    public List<Reservation> findAll() {
        List<Reservation> reservations = this.reservationRepository.findAll();
        return reservations;
    }


    public Reservation findById(int id) {
        logger.info("Reservation Id = " + id);
        Reservation reservation = this.reservationRepository.findById(id).get();
        return reservation;
    }

    public void delete(int id) {
        logger.info("Delete reservation = " + id);
        reservationRepository.delete(findById(id));
    }

    public List<Reservation> findByUser_id(int id) {
        logger.info("User Search = " + id);
        return reservationRepository.findByUser_id(id);
    }

    public List<Reservation> findByUser(String token){
        User user = userService.findUserByToken(token);
        logger.info("find reservation by user = " + user.name);
        List<Reservation> reservations = reservationRepository.findByUser_Id(user.getId());
        return reservations;
    }
}
