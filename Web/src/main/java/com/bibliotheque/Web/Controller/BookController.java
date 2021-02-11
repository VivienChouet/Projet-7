package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.Entity.Dto.BookDTO;
import com.bibliotheque.Web.Entity.Dto.ExemplaireDTO;
import com.bibliotheque.Web.service.BookService;
import com.bibliotheque.Web.service.ExemplaireService;
import com.bibliotheque.Web.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    ExemplaireService exemplaireService;

    @Autowired
    UserService userService;

  @GetMapping(value = "/")
    public String homeBook (Model model) throws IOException, InterruptedException {

       List<BookDTO> books = bookService.listBook();
        model.addAttribute("books", books);
      boolean connected = this.userService.connected();
      boolean admin = this.userService.admin();
      model.addAttribute("connected", connected);
      model.addAttribute("admin", admin);

        return "book/home";
    }

    @GetMapping (value = "/new")
        public String newBook (Model model) throws JsonProcessingException {

       BookDTO book = new BookDTO();
       model.addAttribute("book", book );
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
       return "book/new";
    }



@PostMapping (value = "/new")
    public String newBookPost (@ModelAttribute BookDTO bookDTO, Model model) throws JsonProcessingException {
       model.addAttribute("book", bookDTO);
       bookService.newBook(bookDTO);

       return "book/home";
   }

    @GetMapping (value = "/{id}")
    public String idBook (@PathVariable int id, Model model) throws JsonProcessingException {
        BookDTO bookDTO = bookService.findById(id);
        List<ExemplaireDTO> exemplaireDTOS = exemplaireService.listExemplaireByIdBook(id);
        model.addAttribute("book",bookDTO);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        if(exemplaireDTOS.isEmpty()) {
            return "book/ide";
        }
        model.addAttribute("exemplaires", exemplaireDTOS);
        return "book/id";


    }
}
