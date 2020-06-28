package com.congozo.service.controller;

import com.congozo.service.JwtResponse;
import com.congozo.service.LoginRequest;
import com.congozo.service.MessageResponse;
import com.congozo.service.SignupRequest;
import com.congozo.service.model.Benutzer;
import com.congozo.service.security.UserDetailsImpl;
import com.congozo.service.repository.RoleRepository;
import com.congozo.service.repository.UserRepository;
import com.congozo.service.security.JwtUtils;
import com.congozo.service.model.ERole;
import com.congozo.service.model.CongozoRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.DateUtils;

import javax.validation.Valid;
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
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        switch (signUpRequest.getSingnupType()){
            case EMAIL:
                if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: Email is already in use!"));
                }
                break;
            case NUMBER:
                if (userRepository.existsByHandynummer(signUpRequest.getHandynummer())) {
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: Mobile number is already in use!"));
                }
        }

        // Create new user's account
        Benutzer benutzer = new Benutzer(
                signUpRequest.getVorname(),
                signUpRequest.getNachname(),
                new Date(), //TODO: convert To Date
                signUpRequest.getEmail(),
                signUpRequest.getHandynummer(),
                signUpRequest.getHeimatOrt(),
                encoder.encode(signUpRequest.getPassword()));

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
        benutzer.setCongozoRoles(congozoRoles);
        userRepository.save(benutzer);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
