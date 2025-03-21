package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.Resident;
import com.cit.backend.domain.entity.Profile;
import com.cit.backend.domain.entity.enums.ProfilePermissions;
import com.cit.backend.domain.repository.ResidentRepository;
import com.cit.backend.exceptions.UniqueColumnAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Collection;
import java.util.Set;

@Service
public class ResidentService {
    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private ProfileService profileService;

    public Resident getFromProfile(Profile profile) {
        return residentRepository.findByProfile(profile).orElse(null);
    }

    public Resident save(Resident resident) {
        if(residentRepository.findByCpf(resident.getCpf()).isPresent()) {
            throw new UniqueColumnAlreadyExistsException("CPF already registered");
        }

        Profile profile = resident.getProfile();
        if(profile != null)  {
            profile.setPermissions(Set.of(ProfilePermissions.ROLE_RESIDENT));
            Profile savedProfile = profileService.save(profile);
            resident.setProfile(savedProfile);
        }
        return residentRepository.save(resident);
    }

    public Set<Resident> save(Collection<Resident> residents) {
        return residents.stream()
                .map(this::save)
                .collect(java.util.stream.Collectors.toSet());
    }

    public Resident findByProfile(Profile profile) {
        return residentRepository.findByProfile(profile).orElseThrow(() -> new ResolutionException("User not found"));
    }

    public Resident findUserinfoByProfile(Profile profile) {
        return residentRepository.findByProfile(profile).orElseThrow(() -> new ResolutionException("User not found"));
    }
}
