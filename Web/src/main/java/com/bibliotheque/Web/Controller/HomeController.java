package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.Entity.Dto.BookDTO;
import com.bibliotheque.Web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/")
    public String Home (Model model){

        return "home";

    }
}
