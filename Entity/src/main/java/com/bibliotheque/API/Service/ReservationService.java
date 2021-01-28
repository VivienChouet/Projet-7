package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Dto.ExemplaireDTO;
import com.bibliotheque.API.Entity.Dto.NewReservationDTO;
import com.bibliotheque.API.Entity.Dto.ReservationDTO;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.Reservation;
import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Repository.ExemplaireRepository;
import com.bibliotheque.API.Repository.ReservationRepository;
import com.bibliotheque.API.Repository.UserRepository;
import com.bibliotheque.API.Utility.LoggingController;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.util.Date;
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
    @Autowired
    ExemplaireService exemplaireService;


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

    public Date endReservationDate (Date date){

        DateTime dn = new DateTime(date);
        DateTime date_fin = dn.plusDays(7);
        Date dateFin = date_fin.toDate();
        logger.info("Date de début = " + date);
        logger.info("Date de fin = " + dateFin);
        return dateFin;
    }

    public void extension (int id){
        logger.info("Update Started");
        Reservation reservation = reservationRepository.findById(id).get();
        Date date = reservation.getDate_fin();
        reservation.setDate_fin(endReservationDate(date));
        reservation.setExtension(true);
        reservationRepository.save(reservation);

    }

    public List<Reservation> findByUser(String token){
        String username = userService.findUsernameByToken(token);
        User user = userService.findByUsername(username);
        logger.info("find reservation by user = " + user.name);
        List<Reservation> reservations = reservationRepository.findByUser_IdAndEnded(user.getId(),false);
        return reservations;
    }

    public void endReservation (int id){
        logger.info("ended reservation " + id);
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setEnded(true);
        reservationRepository.save(reservation);
        Exemplaire exemplaire = reservation.getExemplaire();
        exemplaire.setAvailable(true);
        exemplaireRepository.save(exemplaire);

    }

}
