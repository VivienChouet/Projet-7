package com.bibliotheque.batch.DTO;

import lombok.Data;

import java.util.List;

@Data
public class WrapReservationDTO {

    List<ReservationDTO> reservationDTOS;
}
