package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.ReservationDTO;
import com.bibliotheque.batch.DTO.WrapReservationDTO;
import org.springframework.batch.item.ItemReader;

import java.net.http.HttpResponse;
import java.util.List;

public class Reader implements ItemReader<WrapReservationDTO> {
public boolean batch = false;

    public Reader (){
        System.out.println("ca marche ? " + batch);
        batch = false;
    }

    @Override
    public WrapReservationDTO read() throws Exception {
        ReaderAPI readerAPI = new ReaderAPI();
        HttpResponse response = readerAPI.httpResponse();
        List<ReservationDTO> reservationDTOS = readerAPI.reservationDTOS(response);
        if (!batch) {
            WrapReservationDTO wrapReservationDTO = new WrapReservationDTO();
            wrapReservationDTO.setReservationDTOS(reservationDTOS);
            System.out.println("Batch = " + wrapReservationDTO.getReservationDTOS().size());
            batch = true;
            return wrapReservationDTO;

        } else {
          batch = false;
          WrapReservationDTO wrapReservationDTO = new WrapReservationDTO();
          System.out.println("batch = " + batch + "      wrapreservation =  "+ wrapReservationDTO);
            return null;
        }
    }

}
