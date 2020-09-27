package com.bibliotheque.Entity.Controller;


import com.bibliotheque.Entity.Entity.Dto.BookDTO;
import com.bibliotheque.Entity.Entity.Dto.ReservationDTO;
import com.bibliotheque.Entity.Entity.Mapper.ReservationMapper;
import com.bibliotheque.Entity.Entity.Reservation;
import com.bibliotheque.Entity.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationMapper reservationMapper;

    public List<Reservation> listReservation (){
        List<Reservation> reservations = this.reservationService.findAll();
        return reservations;
    }

    @PostMapping("/")
    public ResponseEntity<ReservationDTO> newReservation (@RequestBody ReservationDTO reservationDTO) {
        System.out.println("book => " + reservationMapper.toEntity(reservationDTO));
        reservationService.save(reservationMapper.toEntity(reservationDTO));
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }


}
