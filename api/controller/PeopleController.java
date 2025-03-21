package com.cit.backend.api.controller;

import com.cit.backend.domain.entity.*;
import com.cit.backend.domain.service.PeopleService;
import com.cit.backend.domain.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping
    public ResponseEntity<List<People>> getAllPeople() {
        List<People> people = peopleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(people);
    }

    @GetMapping("/{id}")
    public ResponseEntity<People> getPeopleById(@PathVariable Long id) {
        Optional<People> people = peopleService.findById(id);
        return people.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<People> createPeople(@RequestBody People people) {
        People savedPeople = peopleService.save(people);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPeople);
    }

    @PutMapping("/{id}")
    public ResponseEntity<People> updatePeople(@PathVariable Long id, @RequestBody People people) {
        if (!peopleService.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        people.setId(id);
        People updatedPeople = peopleService.save(people);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPeople);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeople(@PathVariable Long id) {
        if (!peopleService.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        peopleService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}