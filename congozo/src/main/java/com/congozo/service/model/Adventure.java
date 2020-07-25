package com.congozo.service.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ADVENTURE")
public class Adventure extends BaseEntity {

    private String adventureName;

    private Time duration;

    private Date date;

    private Time startTime;

    private BigDecimal cost;

    private Integer maxParticipant;

    private String eligibleFor;

    private Blob picture;

    private String description;

    private String prerequisitesForAttending;

    private String hashtag;

    private String adventurePlace;

    @OneToOne
    @JoinColumn(name = "CONGOZO_USER_ID")
    private CongozoUser CongozoUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adventure")
    private List<AdventurePicture> adventurePictures;

    public String getAdventureName() {
        return adventureName;
    }

    public void setAdventureName(String erlebnisname) {
        this.adventureName = erlebnisname;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time dauer) {
        this.duration = dauer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date datum) {
        this.date = datum;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time uhrZeit) {
        this.startTime = uhrZeit;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal ausgaben) {
        this.cost = ausgaben;
    }

    public Integer getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(Integer maximaleTeilnehmerzahl) {
        this.maxParticipant = maximaleTeilnehmerzahl;
    }

    public String getEligibleFor() {
        return eligibleFor;
    }

    public void setEligibleFor(String geeignetFuer) {
        this.eligibleFor = geeignetFuer;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String beschreibung) {
        this.description = beschreibung;
    }

    public String getPrerequisitesForAttending() {
        return prerequisitesForAttending;
    }

    public void setPrerequisitesForAttending(String teilnahmevoraussetzung) {
        this.prerequisitesForAttending = teilnahmevoraussetzung;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getAdventurePlace() {
        return adventurePlace;
    }

    public void setAdventurePlace(String erlebnissOrt) {
        this.adventurePlace = erlebnissOrt;
    }

    public CongozoUser getCongozoUser() {
        return CongozoUser;
    }

    public void setCongozoUser(CongozoUser congozoUser) {
        this.CongozoUser = congozoUser;
    }

    public List<AdventurePicture> getAdventurePictures() {
        return adventurePictures;
    }

    public void setAdventurePictures(List<AdventurePicture> adventurePictures) {
        this.adventurePictures = adventurePictures;
    }

}
