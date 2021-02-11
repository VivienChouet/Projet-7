package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.service.BookService;
import com.bibliotheque.Web.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String Home (Model model) throws JsonProcessingException {
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);

        return "home";

    }
}
