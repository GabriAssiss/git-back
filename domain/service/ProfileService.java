package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.Profile;
import com.cit.backend.domain.repository.ProfileRepository;
import com.cit.backend.exceptions.UniqueColumnAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return profileRepository.findByEmail(username).orElse(null);
    }

    public Profile save(Profile profile) {
        if(profileRepository.findByEmail(profile.getEmail()).isPresent()) {
            throw new UniqueColumnAlreadyExistsException("Email has already been registered");
        }

        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        return profileRepository.save(profile);
    }

    public Profile findByEmail(String email) {
        return profileRepository.findByEmail(email).orElse(null);
    }
}
