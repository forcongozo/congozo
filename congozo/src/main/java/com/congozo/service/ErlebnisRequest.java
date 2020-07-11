package com.congozo.service;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Time;
import java.util.Date;

public class ErlebnisRequest {
    private String erlebnisname;

    private Time dauer;

    private Date datum;

    private Time startzeit;

    private BigDecimal anfalendeKosten;

    private Integer maximaleTeilnehmer;

    private String geeignetFuer;

    private Blob foto;

    private String beschreibung;

    private String teilnahmevoraussetzung;

    private String hashtag;

    private String erlebnisOrt;

    public String getErlebnisname() {
        return erlebnisname;
    }

    public void setErlebnisname(String erlebnisname) {
        this.erlebnisname = erlebnisname;
    }

    public Time getDauer() {
        return dauer;
    }

    public void setDauer(Time dauer) {
        this.dauer = dauer;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getStartzeit() {
        return startzeit;
    }

    public void setStartzeit(Time startzeit) {
        this.startzeit = startzeit;
    }

    public BigDecimal getAnfalendeKosten() {
        return anfalendeKosten;
    }

    public void setAnfalendeKosten(BigDecimal anfalendeKosten) {
        this.anfalendeKosten = anfalendeKosten;
    }

    public Integer getMaximaleTeilnehmer() {
        return maximaleTeilnehmer;
    }

    public void setMaximaleTeilnehmer(Integer maximaleTeilnehmer) {
        this.maximaleTeilnehmer = maximaleTeilnehmer;
    }

    public String getGeeignetFuer() {
        return geeignetFuer;
    }

    public void setGeeignetFuer(String geeignetFuer) {
        this.geeignetFuer = geeignetFuer;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getTeilnahmevoraussetzung() {
        return teilnahmevoraussetzung;
    }

    public void setTeilnahmevoraussetzung(String teilnahmevoraussetzung) {
        this.teilnahmevoraussetzung = teilnahmevoraussetzung;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getErlebnisOrt() {
        return erlebnisOrt;
    }

    public void setErlebnisOrt(String erlebnisOrt) {
        this.erlebnisOrt = erlebnisOrt;
    }
}
