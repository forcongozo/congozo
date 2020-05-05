package com.congozo.service.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ERLEBNIS")
public class Erlebnis extends BaseEntity {

    private String erlebnisname;

    private Time dauer;

    private Date datum;

    private Time uhrZeit;

    private BigDecimal ausgaben;

    private Integer maximaleTeilnehmerzahl;

    private String Besonderheiten;

    private String ort;

    @OneToOne
    @JoinColumn(name = "benutzer_id")
    private Benutzer benutzer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "erlebnis")
    private List<ErlebnisFoto> erlebnisFotos;

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

    public Time getUhrZeit() {
        return uhrZeit;
    }

    public void setUhrZeit(Time uhrZeit) {
        this.uhrZeit = uhrZeit;
    }

    public BigDecimal getAusgaben() {
        return ausgaben;
    }

    public void setAusgaben(BigDecimal ausgaben) {
        this.ausgaben = ausgaben;
    }

    public Integer getMaximaleTeilnehmerzahl() {
        return maximaleTeilnehmerzahl;
    }

    public void setMaximaleTeilnehmerzahl(Integer maximaleTeilnehmerzahl) {
        this.maximaleTeilnehmerzahl = maximaleTeilnehmerzahl;
    }

    public String getBesonderheiten() {
        return Besonderheiten;
    }

    public void setBesonderheiten(String besonderheiten) {
        Besonderheiten = besonderheiten;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public List<ErlebnisFoto> getErlebnisFotos() {
        return erlebnisFotos;
    }

    public void setErlebnisFotos(List<ErlebnisFoto> erlebnisFotos) {
        this.erlebnisFotos = erlebnisFotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Erlebnis erlebnis = (Erlebnis) o;
        return Objects.equals(erlebnisname, erlebnis.erlebnisname) &&
                Objects.equals(dauer, erlebnis.dauer) &&
                Objects.equals(datum, erlebnis.datum) &&
                Objects.equals(uhrZeit, erlebnis.uhrZeit) &&
                Objects.equals(ausgaben, erlebnis.ausgaben) &&
                Objects.equals(maximaleTeilnehmerzahl, erlebnis.maximaleTeilnehmerzahl) &&
                Objects.equals(Besonderheiten, erlebnis.Besonderheiten) &&
                Objects.equals(ort, erlebnis.ort) &&
                Objects.equals(benutzer, erlebnis.benutzer) &&
                Objects.equals(erlebnisFotos, erlebnis.erlebnisFotos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), erlebnisname, dauer, datum, uhrZeit, ausgaben, maximaleTeilnehmerzahl, Besonderheiten, ort, benutzer, erlebnisFotos);
    }
}
