package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.Entity.Dto.UserDTO;
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
@RequestMapping("/user")
public class LogginController {

    @Autowired
    UserService userService;

    @GetMapping("/connexion")
    public String logginForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("pageTitle", "Connexion");
        model.addAttribute("user", user);
        return "user/connexion";
    }

    @PostMapping("/connexion")
    public String loggin(UserDTO userDTO,Model model) throws JsonProcessingException {
        model.addAttribute("user", userDTO);
        userService.connexion(userDTO);
        return "home";
    }

    @GetMapping("/{id}")
    public String User(@PathVariable int id, Model model) throws JsonProcessingException {
    UserDTO user = userService.idUser(id);

    model.addAttribute("user", user);
        return "user/info";
    }



}
