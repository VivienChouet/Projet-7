package com.bibliotheque.Web.Controller;


import com.bibliotheque.Web.Entity.Dto.ReservationDTO;
import com.bibliotheque.Web.Entity.Dto.UserDTO;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ReservationService reservationService;

    @GetMapping("/")
    public String gestionAdmin (Model model) throws JsonProcessingException {
        List<UserDTO> userDTOS = this.userService.listUser();
        model.addAttribute("users", userDTOS);

        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);

        return "admin/gestion-admin";
    }

    @PostMapping("/{id}")
    public String gestionAdmin (@PathVariable int id){
        return "home";
    }

    @GetMapping("/reservation")
    public String reservationAdmin(Model model) throws JsonProcessingException {
    List<ReservationDTO> reservationDTOS = this.reservationService.listReservation();
    model.addAttribute("reservations", reservationDTOS);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);

    return "admin/gestion-reservation";
    }

}
