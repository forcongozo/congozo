package com.congozo.service;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Time;
import java.util.Date;

public class AdventureRequest {
    private String adventureName;

    private Time duration;

    private Date date;

    private Time startTime;

    private BigDecimal cost;

    private Integer maxParticipant;

    private String eligibleFor;

    private Blob piciture;

    private String description;

    private String prerequisitesForAttending;

    private String hashtag;

    private String adventurePlace;

    public String getAdventureName() {
        return adventureName;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(Integer maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    public String getEligibleFor() {
        return eligibleFor;
    }

    public void setEligibleFor(String eligibleFor) {
        this.eligibleFor = eligibleFor;
    }

    public Blob getPiciture() {
        return piciture;
    }

    public void setPiciture(Blob piciture) {
        this.piciture = piciture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrerequisitesForAttending() {
        return prerequisitesForAttending;
    }

    public void setPrerequisitesForAttending(String prerequisitesForAttending) {
        this.prerequisitesForAttending = prerequisitesForAttending;
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

    public void setAdventurePlace(String adventurePlace) {
        this.adventurePlace = adventurePlace;
    }
}
