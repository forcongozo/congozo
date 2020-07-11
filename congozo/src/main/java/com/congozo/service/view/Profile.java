package com.congozo.service.view;

import com.congozo.service.model.Benutzer;
import org.springframework.stereotype.Component;

@Component
public class Profile {
    private Benutzer benutzer;

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }
}
