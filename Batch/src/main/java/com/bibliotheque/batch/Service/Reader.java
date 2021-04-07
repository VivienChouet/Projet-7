package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.ReservationDTO;
import org.springframework.batch.item.ItemReader;

import java.net.http.HttpResponse;
import java.util.List;

public class Reader implements ItemReader<ReservationDTO> {
    public boolean batch = false;
    private int count = 0;

    public Reader() {
        System.out.println("ca marche ? " + batch);
        batch = false;
    }


    @Override
    public ReservationDTO read() throws Exception {
        ReaderAPI readerAPI = new ReaderAPI();
        HttpResponse response = readerAPI.httpResponse();
        System.out.println("response body = " + response.body());
        System.out.println("response header = " + response.headers());
        if (response.body().toString() != "") {
            List<ReservationDTO> reservationDTOS = readerAPI.reservationDTOS(response);
            if (count < reservationDTOS.size()) {
                System.out.println("Batch = " + reservationDTOS.size());
                return reservationDTOS.get(count++);
            }
            count = 0;
            return null;

        }
        return null;
    }
}
