package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Reservation;
import com.bibliotheque.API.Repository.ReservationRepository;

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


    public List<Reservation> findAll() {
        List<Reservation> reservations = this.reservationRepository.findAll();
        return reservations;
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public Reservation findById(int id) {
        logger.info("Reservation Id = " + id);
        Reservation reservation = this.reservationRepository.findById(id).get();
        return reservation;
    }

    public void delete(int id) {
        logger.info("Delete reservation = " +  id);
        reservationRepository.delete(findById(id));
    }
}
