package com.bibliotheque.API.Controller;



import com.bibliotheque.API.Entity.Dto.ReservationDTO;
import com.bibliotheque.API.Entity.Mapper.ReservationMapper;
import com.bibliotheque.API.Entity.Reservation;
import com.bibliotheque.API.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationMapper reservationMapper;

    @GetMapping("/")
    public ResponseEntity<List<ReservationDTO>> listReservation (){
        List<Reservation> reservations = this.reservationService.findAll();
        return new ResponseEntity<>(reservationMapper.toDto(reservations), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> reservationId (@PathVariable int id){
        Reservation reservation = this.reservationService.findById(id);
        if(reservation == null )
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservationMapper.toDto(reservation),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ReservationDTO> newReservation (@RequestBody ReservationDTO reservationDTO) {
        System.out.println("book => " + reservationMapper.toEntity(reservationDTO));
        reservationService.save(reservationMapper.toEntity(reservationDTO));
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ReservationDTO> updateReservation (@PathVariable int id){
        Reservation reservation = this.reservationService.findById(id);
        if(reservation == null )
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservationMapper.toDto(reservation),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationDTO> deleteReservation (@PathVariable int id){
        reservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
