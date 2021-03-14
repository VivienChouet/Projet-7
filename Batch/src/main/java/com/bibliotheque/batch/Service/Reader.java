package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.WrapReservationDTO;
import org.springframework.batch.item.ItemReader;

public class Reader implements ItemReader<WrapReservationDTO> {

    @Override
    public WrapReservationDTO read() throws Exception {
        int a = 0;
        WrapReservationDTO wrapReservationDTO = new WrapReservationDTO();
        wrapReservationDTO.setReservationDTOS(ReaderAPI.reservationDTOS(ReaderAPI.httpResponse()));

            System.out.println("Batch = " + wrapReservationDTO.getReservationDTOS().size());
                  System.out.println("a " + a);

        return wrapReservationDTO;
    }
}
