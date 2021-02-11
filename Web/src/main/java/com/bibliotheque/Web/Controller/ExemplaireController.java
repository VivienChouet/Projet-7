package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.Entity.Dto.NewExemplaireDTO;
import com.bibliotheque.Web.service.ExemplaireService;
import com.bibliotheque.Web.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exemplaire")
public class ExemplaireController {

    @Autowired
    ExemplaireService exemplaireService;
    @Autowired
    UserService userService;

    @GetMapping("/new/{id}")
    public String newExemplaire(@PathVariable int id,  Model model) throws JsonProcessingException {
        NewExemplaireDTO newExemplaireDTO = new NewExemplaireDTO();
        newExemplaireDTO.setIdBook(id);
        model.addAttribute("exemplaire", newExemplaireDTO);
        boolean connected = this.userService.connected();
        boolean admin = this.userService.admin();
        model.addAttribute("connected", connected);
        model.addAttribute("admin", admin);
        return "exemplaire/new";
    }

 @PostMapping("/new/{id}")
    public ModelAndView newExemplaire (@ModelAttribute NewExemplaireDTO newExemplaireDTO, @PathVariable int id, Model model) throws JsonProcessingException {
        model.addAttribute("exemplaire", newExemplaireDTO);
        newExemplaireDTO.setIdBook(id);
        exemplaireService.save(newExemplaireDTO);

     return new ModelAndView("redirect:/");
    }
}
