package com.congozo.service.controller;

import com.congozo.service.JwtResponse;
import com.congozo.service.LoginRequest;
import com.congozo.service.MessageResponse;
import com.congozo.service.SignupRequest;
import com.congozo.service.model.CongozoUser;
import com.congozo.service.security.UserDetailsImpl;
import com.congozo.service.repository.RoleRepository;
import com.congozo.service.repository.UserRepository;
import com.congozo.service.security.JwtUtils;
import com.congozo.service.model.ERole;
import com.congozo.service.model.CongozoRole;
import com.congozo.service.service.SendeEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private SendeEmail sendeEmail;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                //TODO: Username fixen username != email
                //userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws ParseException {

        switch (signUpRequest.getSingnupType()){
            case EMAIL:
                if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: Email is already in use!"));
                }
                break;
            case NUMBER:
                if (userRepository.existsByMobileNumber(signUpRequest.getMobileNumber())) {
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: Mobile number is already in use!"));
                }
        }
        Date geburtsdatum = null;
        if(signUpRequest.getDateOfBirth() != null ){
            geburtsdatum = new SimpleDateFormat("dd.mm.yyyy").parse(signUpRequest.getDateOfBirth());
        }
        // Create new user's account
        /**
         * String vorname, String nachname, String geschlecht, String username,
         *                     Date geburtsdatum, String email, String handynummer,String password,
         *                     String info, String stadt, String land, String hashtag
         */
        CongozoUser CongozoUser = new CongozoUser(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getGender(),
                getUsernameOrEmail(signUpRequest.getEmail(), signUpRequest.getFirstName()),
                geburtsdatum,
                signUpRequest.getEmail(),
                signUpRequest.getMobileNumber(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getInfo(),
                signUpRequest.getCity(),
                signUpRequest.getCountry(),
                signUpRequest.getHashtag()
                );

        Set<String> strRoles = signUpRequest.getRole();
        Set<CongozoRole> congozoRoles = new HashSet<>();

        if (strRoles == null) {
            CongozoRole userCongozoRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            congozoRoles.add(userCongozoRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        CongozoRole adminCongozoRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        congozoRoles.add(adminCongozoRole);

                        break;
                    case "mod":
                        CongozoRole modCongozoRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        congozoRoles.add(modCongozoRole);

                        break;
                    default:
                        CongozoRole userCongozoRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        congozoRoles.add(userCongozoRole);
                }
            });
        }
        CongozoUser.setCongozoRoles(congozoRoles);
        userRepository.save(CongozoUser);
        //TODO: Async implementieren
//        Email email = new Email();
//        email.setTo(benutzer.getEmail());
//        email.setSubject("Bitte bestätigen");
//        email.setMessageText("This is a sample text message.");
//        sendeEmail.sendMail(email);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private String getUsernameOrEmail(String email, String username){
        if(username != null){
            return username;
        } else {
            return email;
        }
    }
}
