package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Reservation;
import com.bibliotheque.API.Repository.ReservationRepository;
import com.bibliotheque.API.Repository.UserRepository;
import com.bibliotheque.API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BatchService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    public List<Reservation> batch() {
        logger.info("Batch en cours");
        List<Reservation> reservations = reservationRepository.findByEnded(false);
        List<Reservation> reservations1 = null;
        Date today = new Date();
        for (int i = 0; i < reservations.size(); i++
        ) {
            if (reservations.get(i).getDate_debut().before(today)) {
                reservations1 = reservations;
            }
        }
        logger.info("batch envoyé = " + reservations1.size());
                return reservations1;

    }
}
