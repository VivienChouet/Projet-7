package com.bibliotheque.API.Repository;

import com.bibliotheque.API.Entity.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {


    Exemplaire findById(int id);
    List<Exemplaire> findByBook_IdAndEdition(int id, String edition);

    List<Exemplaire> findByAvailable(boolean b);

    List<Exemplaire> findByBook_idAndAvailable(int id, boolean b);
}
