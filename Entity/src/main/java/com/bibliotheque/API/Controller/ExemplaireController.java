package com.bibliotheque.API.Controller;


import com.bibliotheque.API.Entity.Dto.ExemplaireDTO;
import com.bibliotheque.API.Entity.Dto.NewExemplaireDTO;
import com.bibliotheque.API.Entity.Dto.ReservationResearchDTO;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.Mapper.ExemplaireMapper;
import com.bibliotheque.API.Service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exemplaire")
public class ExemplaireController {

    @Autowired
    ExemplaireService exemplaireService;

    @Autowired
    ExemplaireMapper exemplaireMapper;



    @GetMapping("/")
    public ResponseEntity<List<ExemplaireDTO>> lsitExemplaire (){
        List<Exemplaire> exemplaires = this.exemplaireService.findAll();
        return new ResponseEntity<>(exemplaireMapper.toDto(exemplaires), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<ExemplaireDTO>> lsitExemplaireAvailable (){
        List<Exemplaire> exemplaires = this.exemplaireService.findbyAvailable();
        return new ResponseEntity<>(exemplaireMapper.toDto(exemplaires), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExemplaireDTO> exemplaireId (@PathVariable int id){
          Exemplaire exemplaire = this.exemplaireService.findById(id);
          if (exemplaire == null)
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exemplaireMapper.toDto(exemplaire), HttpStatus.OK);
    }

    @PostMapping("/")
    public  ResponseEntity<ExemplaireDTO> newExemplaire (@RequestBody NewExemplaireDTO newExemplaireDTO){
        exemplaireService.save(newExemplaireDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<ExemplaireDTO> deleteExemplaire (@RequestBody NewExemplaireDTO newExemplaireDTO){
        exemplaireService.delete(newExemplaireDTO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ExemplaireDTO> updateExemplaire (@RequestBody NewExemplaireDTO newExemplaireDTO){
        exemplaireService.save(newExemplaireDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/book/available/{id}")
    public ResponseEntity<List<ExemplaireDTO>> listExemplaireByBookId (@PathVariable int id){
        List<Exemplaire> exemplaires = this.exemplaireService.findByBook_idAndAvailable(id);
        if (exemplaires == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exemplaireMapper.toDto(exemplaires), HttpStatus.OK);
    }

    @PostMapping("/edition")
        public ResponseEntity<List<ExemplaireDTO>> listExemplaireByEditionAndBook (@RequestBody ReservationResearchDTO reservationResearchDTO){
        List<Exemplaire> exemplaires = this.exemplaireService.findByBook_idAndEdition(reservationResearchDTO.getId(),reservationResearchDTO.getEdition());

        return new ResponseEntity<>(exemplaireMapper.toDto(exemplaires),HttpStatus.OK);
    }

}
