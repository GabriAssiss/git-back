package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.People;
import com.cit.backend.domain.entity.Profile;
import com.cit.backend.domain.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository<People> peopleRepository;

    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    public Optional<People> findById(Long id) {
        return peopleRepository.findById(id);
    }

    public People save(People people) {
        return peopleRepository.save(people);
    }

    public void deleteById(Long id) {
        peopleRepository.deleteById(id);
    }

    public People findByProfile(Profile profile) { return peopleRepository.findByProfile(profile).orElseThrow(() -> new ResolutionException("User not found")); }
}