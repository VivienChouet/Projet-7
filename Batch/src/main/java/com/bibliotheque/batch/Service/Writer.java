package com.bibliotheque.batch.Service;

import org.springframework.batch.item.ItemWriter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;

public class Writer implements ItemWriter<SimpleMailMessage> {

    private JavaMailSender javaMailSender;

    public Writer(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void write(List<? extends SimpleMailMessage> list) throws Exception {
        System.out.println("Writer : ");
        JavaMailSender javaMailSender = new JavaMailSenderImpl();
        for (SimpleMailMessage simpleMailMessage:list){
            if(simpleMailMessage != null){
                javaMailSender.send(simpleMailMessage);
            }
        }
    }
}
