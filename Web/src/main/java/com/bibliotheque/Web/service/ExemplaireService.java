package com.bibliotheque.Web.service;

import com.bibliotheque.Web.Entity.Dto.ExemplaireDTO;
import com.bibliotheque.Web.Entity.Dto.NewExemplaireDTO;
import com.bibliotheque.Web.Entity.Dto.NumberExemplaireDTO;
import com.bibliotheque.Web.utility.OperateurDiamant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.*;

@Service
public class ExemplaireService {

    @Autowired
    OperateurDiamant operateurDiamant;


    /**
     * @param id
     * @return List<ExemplaireDTO>
     * @throws JsonProcessingException
     */
    public List<ExemplaireDTO> listExemplaireByIdBookAndAvailable(int id) throws JsonProcessingException {
        HttpResponse response = operateurDiamant.Request("http://localhost:8080/exemplaire/book/available/" + id);
        List<ExemplaireDTO> exemplaireDTOS = operateurDiamant.listObject(response, ExemplaireDTO.class);
        return exemplaireDTOS;
    }

    /**
     * @param newExemplaireDTO
     * @throws JsonProcessingException
     */
    public void save(NewExemplaireDTO newExemplaireDTO) throws JsonProcessingException {
        String json = (String) operateurDiamant.jsonConvert(newExemplaireDTO);
        operateurDiamant.post("http://localhost:8080/exemplaire/", json);
    }

    public List<NumberExemplaireDTO> CountExemplaire(List<ExemplaireDTO> exemplaireDTOS) {
        List<NumberExemplaireDTO> numberExemplaireDTOS = new ArrayList<NumberExemplaireDTO>();
        for (int i = 0; i < exemplaireDTOS.size(); i++) {
            NumberExemplaireDTO numberExemplaireDTO = new NumberExemplaireDTO();
            String edition = exemplaireDTOS.get(i).edition;
            int count = 0;
            for (int u = 0; u < exemplaireDTOS.size(); u++)
                if (exemplaireDTOS.get(u).edition.equals(edition)) {
                    count++;
                    System.out.println("edition : " + edition);
                    System.out.println("count : " + count);
                }
            numberExemplaireDTO.setName(edition);
            numberExemplaireDTO.setNumber(count);
            numberExemplaireDTOS.add(i, numberExemplaireDTO);
        }
        return numberExemplaireDTOS;
    }

    public List<NumberExemplaireDTO> CountExemplaireWithoutDouble (List<ExemplaireDTO> exemplaireDTOS){
        List<NumberExemplaireDTO>numberExemplaireDTOS = CountExemplaire(exemplaireDTOS);
        List<NumberExemplaireDTO> list = new LinkedList<>(numberExemplaireDTOS);
        Collections.sort(list, new Comparator<NumberExemplaireDTO>() {
            @Override
            public int compare(NumberExemplaireDTO o1, NumberExemplaireDTO o2) {
                return (o1.getName().compareTo(o2.getName()));
            }
        });
        LinkedHashMap resultat = new LinkedHashMap();
        for (NumberExemplaireDTO entree : list){
            resultat.put(entree.getName(), entree.getNumber());
        }
        System.out.println(resultat);
        List<String> ListEditions = new ArrayList<String>(resultat.keySet());
        List<Integer> ListNumber = new ArrayList<Integer>(resultat.values());
        System.out.println("Liste des edition disponible : " + ListEditions);
        System.out.println("Number d'exemplaire disponible par edition : " + ListNumber);
        List<NumberExemplaireDTO>numberExemplaireDTOS1 = new ArrayList<NumberExemplaireDTO>();
        for (int i = 0; i<ListEditions.size();i++)
        {
            NumberExemplaireDTO numberExemplaireDTO = new NumberExemplaireDTO();
            numberExemplaireDTO.setNumber(ListNumber.get(i));
            numberExemplaireDTO.setName(ListEditions.get(i));
            numberExemplaireDTOS1.add(i,numberExemplaireDTO);
        }
        System.out.println("number Edition test : " + numberExemplaireDTOS1);
        return  numberExemplaireDTOS1;
    }

    private TreeMap<String, Integer> exemplaire;

  /*  public void testmap (List<ExemplaireDTO> exemplaireDTOS){
        this.exemplaire = new TreeMap<>();
        String edition;
        for (int i =0 ; i<exemplaireDTOS.size() ; i++){
            if (this.exemplaire.get(edition)==null){

            }
        }

    }*/
}
