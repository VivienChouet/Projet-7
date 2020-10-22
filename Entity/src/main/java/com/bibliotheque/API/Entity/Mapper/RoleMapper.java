package com.bibliotheque.API.Entity.Mapper;

import com.bibliotheque.API.Entity.Dto.ReservationDTO;
import com.bibliotheque.API.Entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<ReservationDTO, Reservation> {
}
