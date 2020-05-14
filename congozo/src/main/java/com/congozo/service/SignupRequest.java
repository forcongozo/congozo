package com.congozo.service;

import com.congozo.service.model.SingnupType;

import java.util.Set;

public class SignupRequest {
    private String vorname;
    private String nachname;
    private String geschlecht;
    private String geburtsDatum;
    private String heimatOrt;
    private String email;
    private String handynummer;
    private String password;
    private Set<String> role;

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(String geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }

    public String getHeimatOrt() {
        return heimatOrt;
    }

    public void setHeimatOrt(String heimatOrt) {
        this.heimatOrt = heimatOrt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHandynummer() {
        return handynummer;
    }

    public void setHandynummer(String handynummer) {
        this.handynummer = handynummer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public SingnupType getSingnupType(){
        if(!this.email.isEmpty()){
            return SingnupType.EMAIL;
        }
        if(! this.handynummer.isEmpty()){
            return SingnupType.NUMBER;
        }
        return null;
    }

}
