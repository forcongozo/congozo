package com.congozo.service.controller;

import com.congozo.service.ErlebnisRequest;
import com.congozo.service.SignupRequest;
import com.congozo.service.model.Benutzer;
import com.congozo.service.model.Email;
import com.congozo.service.model.Erlebnis;
import com.congozo.service.repository.ErlebnisRepository;
import com.congozo.service.service.SendeEmail;
import com.congozo.service.service.UserDetailsServiceImpl;
import com.congozo.service.view.Profile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private ErlebnisRepository erlebnisRepository;


    @Autowired
    private Profile profile;

    @PostMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Benutzer userAccess() {
        return profile.getBenutzer();
    }

    @PostMapping("/erlebnissPosten")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Erlebnis registerErlebnis(@Valid @RequestBody ErlebnisRequest erlebnisRequest){
        Erlebnis erlebnis = new Erlebnis();
        erlebnis.setBenutzer(profile.getBenutzer());
        erlebnis.setErlebnisname(erlebnisRequest.getErlebnisname());
        erlebnis.setDauer(erlebnisRequest.getDauer());
        erlebnis.setDatum(erlebnisRequest.getDatum());
        erlebnis.setUhrZeit(erlebnisRequest.getStartzeit());
        erlebnis.setAusgaben(erlebnisRequest.getAnfalendeKosten());
        erlebnis.setMaximaleTeilnehmerzahl(erlebnisRequest.getMaximaleTeilnehmer());
        erlebnis.setGeeignetFuer(erlebnisRequest.getGeeignetFuer());
        erlebnis.setFoto(erlebnisRequest.getFoto());
        erlebnis.setBeschreibung(erlebnisRequest.getBeschreibung());
        erlebnis.setTeilnahmevoraussetzung(erlebnisRequest.getTeilnahmevoraussetzung());
        erlebnis.setHashtag(erlebnisRequest.getHashtag());
        erlebnis.setErlebnissOrt(erlebnisRequest.getErlebnisOrt());

        return erlebnisRepository.save(erlebnis);

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
