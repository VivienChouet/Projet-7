package com.bibliotheque.API.Controller;


import com.bibliotheque.API.Entity.Dto.ExemplaireDTO;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.Mapper.ExemplaireMapper;
import com.bibliotheque.API.Repository.ExemplaireRepository;
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

    @GetMapping("/{id}")
    public ResponseEntity<ExemplaireDTO> exemplaireId (@PathVariable int id){
          Exemplaire exemplaire = this.exemplaireService.findById(id);
          if (exemplaire == null)
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exemplaireMapper.toDto(exemplaire), HttpStatus.OK);
    }

    @PostMapping("/")
    public  ResponseEntity<ExemplaireDTO> newExemplaire (@RequestBody ExemplaireDTO exemplaireDTO){
        exemplaireService.save(exemplaireMapper.toEntity(exemplaireDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExemplaireDTO> deleteExemplaire (@PathVariable int id){
        exemplaireService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ExemplaireDTO> updateExemplaire (@RequestBody ExemplaireDTO exemplaireDTO){
        exemplaireService.save(exemplaireMapper.toEntity(exemplaireDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<ExemplaireDTO>> listExemplaireByBookId (@PathVariable int id){
        List<Exemplaire> exemplaires = this.exemplaireService.findByBook_id(id);
        if (exemplaires == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exemplaireMapper.toDto(exemplaires), HttpStatus.OK);
    }

}
