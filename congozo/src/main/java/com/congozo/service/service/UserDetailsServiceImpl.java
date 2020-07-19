package com.congozo.service.service;

import com.congozo.service.model.Benutzer;
import com.congozo.service.security.UserDetailsImpl;
import com.congozo.service.repository.UserRepository;
import com.congozo.service.view.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Profile profile;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Benutzer benutzer = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with Email address: " + email));
        profile.setBenutzer(benutzer);
        return UserDetailsImpl.build(benutzer);
    }
}
