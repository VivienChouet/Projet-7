package com.bibliotheque.Entity.Service;

import com.bibliotheque.Entity.Entity.Reservation;
import com.bibliotheque.Entity.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {


@Autowired
ReservationRepository reservationRepository;


    public List<Reservation> findAll() {
        List<Reservation> reservations = this.reservationRepository.findAll();
        return reservations;
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
