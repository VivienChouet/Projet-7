package com.bibliotheque.Web.Controller;

import com.bibliotheque.Web.Entity.Dto.ExemplaireDTO;
import com.bibliotheque.Web.service.ExemplaireService;
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

    @GetMapping("/")
    public String newExemplaire(Model model){
        ExemplaireDTO exemplaireDTO = new ExemplaireDTO();
        model.addAttribute("exemplaire", exemplaireDTO);

        return "exemplaire/new";
    }

    @PostMapping("/")
    public String newExemplaire (@ModelAttribute ExemplaireDTO exemplaireDTO,@PathVariable int id, Model model){
        model.addAttribute("exemplaire", exemplaireDTO);
        exemplaireService.save(exemplaireDTO,id);

        return "home";
    }
}
