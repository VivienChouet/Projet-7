package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.WrapReservationDTO;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class Writer implements ItemWriter<WrapReservationDTO> {


    public Writer(){

    }


    @Override
    public void write(List<? extends WrapReservationDTO> reservationDTOS) throws Exception {
        System.out.println("size writer : " + reservationDTOS.size());
        for (int i =0 ; i < reservationDTOS.size();i++){
            System.out.println(reservationDTOS.get(i).getReservationDTOS().get(i).getId());
        }

    }
}
