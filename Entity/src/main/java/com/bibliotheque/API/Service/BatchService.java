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


    /**
     * batch
     * @return List<Reservation>
     */
    public List<Reservation> batch() {
        logger.info("Batch en cours");
        List<Reservation> reservations = reservationRepository.findByEndedAndBatch(false, false);
        List<Reservation> reservations1 = null;
        Date today = new Date();
        logger.info("reservation = " + reservations.size());
        if (reservations.size() != 0){
            for (int i = 0; i < reservations.size(); i++
            ) {
                if (reservations.get(i).getDate_fin().before(today)) {
                    reservations1 = reservations;
                }
            }
//            logger.info("batch envoyé = " + reservations1.size());
            return reservations1;

        }
        return null;
    }

    /**
     * Batch done
     * @param reservation
     */
    public void update(Reservation reservation) {
        logger.info("update reservation id : " + reservation.id + " batch done");
        reservation.setBatch(true);
        reservationRepository.save(reservation);
    }
}
