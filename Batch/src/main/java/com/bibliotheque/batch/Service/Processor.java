package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.ReservationDTO;
import com.bibliotheque.batch.DTO.WrapReservationDTO;
import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<WrapReservationDTO, WrapReservationDTO> {

    @Override
    public WrapReservationDTO process(WrapReservationDTO wrapReservationDTO) throws Exception {
        for (int i = 0; i < wrapReservationDTO.getReservationDTOS().size(); i++) {
            ReservationDTO reservationDTO = wrapReservationDTO.getReservationDTOS().get(i);
            System.out.println("email = " + reservationDTO.getUser().getEmail());
            System.out.println("user name = " + reservationDTO.getUser().getName());
            System.out.println("book = " + reservationDTO.getExemplaire().getBook().getTitle());
/*
            Email from = new Email("slaschh@gmail.com");
            String subject = "Livre à retourner : " + reservationDTO.getExemplaire().getBook().getTitle();
            Email to = new Email(reservationDTO.getUser().getEmail());
            Content content = new Content("text/plain", "Bonjour Mr/Mme " + reservationDTO.getUser().getName() + ' ' + ",\n" +
                    "Vous avez emprunté le livre : " + reservationDTO.getExemplaire().getBook().getTitle() + " édition  : " + reservationDTO.getExemplaire().getEdition() + ".\n" +
                    "Vous deviez le rendre le : " + reservationDTO.getDate_fin() + ".\n" +
                    "Merci de faire le necessaire pour le rendre dans les plus brefs délais.");
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
                System.out.println(request.getHeaders());
                System.out.println(request.getBody());
                System.out.println("sg : " + sg.getRequestHeaders());
            } catch (IOException ex) {
                throw ex;
            }*/
            System.out.println("Valeur de i = " + i);
            //reservationDTO.setBatch(true);
        }
        return wrapReservationDTO;
    }
}