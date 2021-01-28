package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.Entity.Dto.ReservationDTO;
import com.bibliotheque.Web.service.ReservationService;
import com.bibliotheque.Web.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @PostMapping("/{id}")
    public String newReservation(@PathVariable int id, Model model) throws JsonProcessingException {
        System.out.println("est ce que ca Save ?????????");
        reservationService.newReservation(id);
        return "home";
    }

    @GetMapping("/myreservation")
    public String myReservation(Model model) throws JsonProcessingException {
        List<ReservationDTO> reservations = this.reservationService.reservationByUser();
        model.addAttribute("reservations", reservations);
        return "reservation/myReservation";
    }

    @PostMapping("/extension/{id}")
    public String extension (@PathVariable int id, Model model){
reservationService.extension(id);
        return "home";
    }

    @PostMapping("/end/{id}")
    public String endedReservation (@PathVariable int id) throws JsonProcessingException {
    reservationService.endedReservation(id);
        return "home";
    }


}
