package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.WrapReservationDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;

public class Processor implements ItemProcessor<WrapReservationDTO, SimpleMailMessage> {

    @Override
    public SimpleMailMessage process(WrapReservationDTO reservationDTO) throws Exception {
    System.out.println("ESt ce que ca ce lance ?????" + reservationDTO.getReservationDTOS().size());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      /*  simpleMailMessage.setTo(reservationDTO.getReservationDTOS(getUser().getEmail()));
        simpleMailMessage.setSubject("Livre a retourner : " + reservationDTO.getExemplaire().getBook().getTitle());
        simpleMailMessage.setText("Bonjour " + reservationDTO.getUser().getName() + ",\n" +
                "Vous avez a ce jour en votre possesion de le livre " + reservationDTO.getExemplaire().getBook().getTitle() + " vous  deviez le rendre le : " + reservationDTO.getDate_fin() + ".\n" +
                "Merci de faire le necessaire pour le rendre dans les plus brefs délai.\n" +
                "Cordialement Bibliotheque de XXXXXXX");
*/
        return simpleMailMessage;
    }
}
