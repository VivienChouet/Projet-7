package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.ExemplaireDTO;
import com.bibliotheque.Web.Entity.Dto.NewExemplaireDTO;
import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ExemplaireService {

    @Autowired
    OperateurDiamant operateurDiamant;


    public List<ExemplaireDTO> listExemplaireByIdBook(int id) throws JsonProcessingException {

        HttpResponse response = operateurDiamant.Request("http://localhost:8080/exemplaire/book/" + id);
        List<ExemplaireDTO> exemplaireDTOS = operateurDiamant.listObject(response, ExemplaireDTO.class);

        return exemplaireDTOS;
    }

    public void save( NewExemplaireDTO newExemplaireDTO) throws JsonProcessingException {


        String json = (String) operateurDiamant.jsonConvert(newExemplaireDTO);
        operateurDiamant.post("http://localhost:8080/exemplaire/", json);


    }
}
