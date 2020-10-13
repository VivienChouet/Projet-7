package com.bibliotheque.API.Repository;

import com.bibliotheque.API.Entity.Reservation;
import com.bibliotheque.API.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    List<Reservation> findByUser_id(int id);
}
