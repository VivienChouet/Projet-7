package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.Entity.Dto.ExemplaireDTO;
import com.bibliotheque.Web.Entity.Dto.NewExemplaireDTO;
import com.bibliotheque.Web.service.ExemplaireService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exemplaire")
public class ExemplaireController {

    @Autowired
    ExemplaireService exemplaireService;

    @GetMapping("/new/{id}")
    public String newExemplaire(@PathVariable int id,  Model model){
        NewExemplaireDTO newExemplaireDTO = new NewExemplaireDTO();
        newExemplaireDTO.setIdBook(id);
        model.addAttribute("exemplaire", newExemplaireDTO);

        return "exemplaire/new";
    }

 @PostMapping("/new/{id}")
    public String newExemplaire (@ModelAttribute NewExemplaireDTO newExemplaireDTO, @PathVariable int id, Model model) throws JsonProcessingException {
        model.addAttribute("exemplaire", newExemplaireDTO);
        newExemplaireDTO.setIdBook(id);
        exemplaireService.save(newExemplaireDTO);

        return "home";
    }
}
