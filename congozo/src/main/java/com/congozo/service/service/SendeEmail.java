package com.congozo.service.service;

import com.congozo.service.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendeEmail {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendMail(final Email email){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setFrom(email.getFrom());
        simpleMailMessage.setTo(email.getTo());
        simpleMailMessage.setText(email.getMessageText());
        javaMailSender.send(simpleMailMessage);
    }
}
