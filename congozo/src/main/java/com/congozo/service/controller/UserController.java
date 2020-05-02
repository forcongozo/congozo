package com.congozo.service.controller;

import com.congozo.service.model.Geschlecht;
import com.congozo.service.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.DateUtils;

@RestController
public class UserController {

    @GetMapping(path = "/")
    public User getUser(){
        return testUser();
    }

    private User testUser(){
        User user = new User();
        user.setVorname("vorname");
        user.setNachname("nachname");
        user.setEmail("vorname.nachname@congozo.com");
        user.setGeburtsdatum(DateUtils.create(2020,04,29).getTime());
        user.setGeschlecht(Geschlecht.M);
        user.setHeimatOrt("Wien");

        return user;
    }
}
