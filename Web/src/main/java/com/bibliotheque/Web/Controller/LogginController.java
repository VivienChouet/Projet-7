package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loggin")
public class LogginController {



    @GetMapping ("/")
    public String logginForm (Model model){
    User user = new User();
    model.addAttribute("user", user);
    model.addAttribute("pageTitle", "Loggin");
    return "loggin/loggin";
    }



}
