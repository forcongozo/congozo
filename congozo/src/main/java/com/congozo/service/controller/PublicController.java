package com.congozo.service.controller;

import com.congozo.service.AdventureRequest;
import com.congozo.service.model.CongozoUser;
import com.congozo.service.model.Adventure;
import com.congozo.service.repository.AdventureRepository;
import com.congozo.service.view.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private AdventureRepository adventureRepository;


    @Autowired
    private Profile profile;

    @PostMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public CongozoUser userAccess() {
        return profile.getCongozoUser();
    }

    @PostMapping("/newAdventure")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Adventure registerErlebnis(@Valid @RequestBody AdventureRequest adventureRequest){
        Adventure adventure = new Adventure();
        adventure.setCongozoUser(profile.getCongozoUser());
        adventure.setAdventureName(adventureRequest.getAdventureName());
        adventure.setDuration(adventureRequest.getDuration());
        adventure.setDate(adventureRequest.getDate());
        adventure.setStartTime(adventureRequest.getStartTime());
        adventure.setCost(adventureRequest.getCost());
        adventure.setMaxParticipant(adventureRequest.getMaxParticipant());
        adventure.setEligibleFor(adventureRequest.getEligibleFor());
        adventure.setPicture(adventureRequest.getPiciture());
        adventure.setDescription(adventureRequest.getDescription());
        adventure.setPrerequisitesForAttending(adventureRequest.getPrerequisitesForAttending());
        adventure.setHashtag(adventureRequest.getHashtag());
        adventure.setAdventurePlace(adventureRequest.getAdventurePlace());

        return adventureRepository.save(adventure);

    }

    @PostMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
