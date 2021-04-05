package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.ReservationDTO;
import com.bibliotheque.batch.DTO.WrapReservationDTO;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class Sendgrid {

    public static void test(WrapReservationDTO wrapReservationDTO) throws IOException {

        for (int i = 0; i < wrapReservationDTO.getReservationDTOS().size(); i++) {
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO = wrapReservationDTO.getReservationDTOS().get(i);
            System.out.println("email = " + reservationDTO.getUser().getEmail());
            System.out.println("user name = " + reservationDTO.getUser().getName());
            System.out.println("book = " + reservationDTO.getExemplaire().getBook().getTitle());
            Email from = new Email("slaschh@gmail.com");
            String subject = "Sending with SendGrid is Fun";
            Email to = new Email("slaschh@gmail.com");
            Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
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
            } catch (IOException ex) {
                throw ex;
            }
        }
    }
}
