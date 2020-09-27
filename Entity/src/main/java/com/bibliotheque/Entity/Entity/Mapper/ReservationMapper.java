package com.bibliotheque.Entity.Entity.Mapper;

import com.bibliotheque.Entity.Entity.Dto.ReservationDTO;
import com.bibliotheque.Entity.Entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends EntityMapper<ReservationDTO, Reservation> {
}
