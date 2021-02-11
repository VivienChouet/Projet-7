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
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/connexion")
    public String logginForm(Model model) throws JsonProcessingException {
        UserDTO user = new UserDTO();
        model.addAttribute("pageTitle", "Connexion");
        model.addAttribute("user", user);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        return "user/connexion";
    }

    @PostMapping("/connexion")
    public ModelAndView loggin(UserDTO userDTO, Model model) throws JsonProcessingException {
        model.addAttribute("user", userDTO);
        userService.connexion(userDTO);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/{id}")
    public String User(@PathVariable int id, Model model) throws JsonProcessingException {
    UserDTO user = userService.idUser(id);
    model.addAttribute("user", user);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        return "user/info";
    }

    @GetMapping("/logout")
    public ModelAndView logout(Model model) throws JsonProcessingException {
        userService.logout();
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/register")
    public String register(Model model) throws JsonProcessingException {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
         return "user/register";
    }

    @PostMapping("/register")
    public ModelAndView newUser (UserDTO userDTO) throws JsonProcessingException {
        userService.newUser(userDTO);
        return new ModelAndView("redirect:/");
    }

}
