package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Book;
import com.bibliotheque.API.Entity.Dto.NewExemplaireDTO;
import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Repository.ExemplaireRepository;
import com.bibliotheque.API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExemplaireService {

    @Autowired
    ExemplaireRepository exemplaireRepository;

    @Autowired
            BookService bookService;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    /**
     * find All
     * @return List<Exemplaire>
     */
    public List<Exemplaire> findAll() {
        logger.info("List Exemplaire");
        List<Exemplaire> exemplaires = this.exemplaireRepository.findAll();
        return exemplaires;
    }

    /**
     * findById
     * @param id
     * @return Exemplaire
     */
    public Exemplaire findById(int id) {
        logger.info("Exemplaire id " + id);
        Exemplaire exemplaire = this.exemplaireRepository.findById(id);
        return exemplaire;
    }

    /**
     * Save
     * @param newExemplaireDTO
     */
    public void save(NewExemplaireDTO newExemplaireDTO) {
        logger.info("save new exemplaire = ");
        Exemplaire exemplaire = new Exemplaire();
        Book book = bookService.findById(newExemplaireDTO.getIdBook());
        exemplaire.setBook(book);
        exemplaire.setEdition(newExemplaireDTO.getEdition());
        exemplaire.setAvailable(true);
        exemplaireRepository.save(exemplaire);
    }

    /**
     * Delete
     * @param id
     */
    public void delete(int id) {
        logger.info("delete = " + id);
        Exemplaire exemplaire = this.exemplaireRepository.findById(id);
        exemplaireRepository.delete(exemplaire);
    }

    /**
     * find Exemplaire By Book_id
     * @param id
     * @return List<Exemplaire>
     */
    public List<Exemplaire> findByBook_idAndAvailable(int id) {
        logger.info("find Exemplaire by Book Id = " + id);
        List<Exemplaire> exemplaires = this.exemplaireRepository.findByBook_idAndAvailable(id, true);
        return exemplaires;
    }

    public List<Exemplaire> findByBook_idAndEdition(int id, String edition) {
        logger.info("Search By book_id : " + id + " And by edition : " + edition);
        List<Exemplaire> exemplaires = this.exemplaireRepository.findByBook_IdAndEdition(id, edition);
        return exemplaires;
    }

    public List<Exemplaire> findbyAvailable() {
        List<Exemplaire> exemplaires = this.exemplaireRepository.findByAvailable(true);
        return exemplaires;
    }
}

