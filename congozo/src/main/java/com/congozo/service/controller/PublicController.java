package com.congozo.service.controller;

import com.congozo.service.model.Benutzer;
import com.congozo.service.model.Email;
import com.congozo.service.service.SendeEmail;
import com.congozo.service.service.UserDetailsServiceImpl;
import com.congozo.service.view.Profile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private SendeEmail sendeEmail;


    @Autowired
    @Qualifier("Congozouser")
    private UserDetails userDetails;

    @Autowired
    private Profile profile;

    @GetMapping("/all")
    public String allAccess() {
        Email email = new Email();
        email.setFrom("hamid-amiri2011@hotmail.com");
        email.setTo("amirihamid014@gmail.com");
        email.setSubject("This is a test mail");
        email.setMessageText("This is a sample text message.");
        sendeEmail.sendMail(email);
        return "Public Content.";
    }


    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Benutzer userAccess() {
        return profile.getBenutzer();
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
