package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/{id}")
    public String newReservation (@PathVariable int id, Model model){


        return "reservation/new";
    }

}
