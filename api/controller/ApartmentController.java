package com.cit.backend.api.controller;

import com.cit.backend.api.mapper.ApartmentMapper;
import com.cit.backend.api.request.ApartmentRequest;
import com.cit.backend.api.response.ApartmentResponse;
import com.cit.backend.api.validator.JWTToken;
import com.cit.backend.domain.entity.Apartment;
import com.cit.backend.domain.service.ApartmentService;
import com.cit.backend.exceptions.InvalidApartmentTokenException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ApartmentMapper apartmentMapper;

    @GetMapping("/{id:[\\d+]}")
    public ResponseEntity<ApartmentResponse> getApartment(@PathVariable("id") Long id) {
        Apartment apartment = apartmentService.findById(id);
        if (apartment == null) {
            return ResponseEntity.notFound().build();
        }

        ApartmentResponse response = apartmentMapper.toApartmentResponse(apartment);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-token/{token:^(?:[A-Za-z0-9-_]+(?:\\.|$)){3}}")
    public ResponseEntity<ApartmentResponse> getApartment(@PathVariable("token") @Valid @JWTToken String token) {
        Apartment apartment = apartmentService.findByToken(token);
        if (apartment == null) {
            return ResponseEntity.notFound().build();
        }

        ApartmentResponse response = apartmentMapper.toApartmentResponse(apartment);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-unit/{unitId:[\\d+]}")
    public ResponseEntity<List<ApartmentResponse>> getApartmentsByUnit(@PathVariable("unitId") Long unitId) {
        List<Apartment> apartments = apartmentService.findAllByUnitId(unitId);
        List<ApartmentResponse> response = apartmentMapper.toApartmentResponseList(apartments);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/{token:^(?:[A-Za-z0-9-_]+(?:\\.|$)){3}}")
    public ResponseEntity<ApartmentResponse> register(@PathVariable("token") @Valid @JWTToken String token, @RequestBody ApartmentRequest request) {
        Apartment apartment = apartmentService.findByToken(token);
        if (apartment == null) {
            throw new InvalidApartmentTokenException("Apartment not found");
        }

        Apartment apartmentUpdated = apartmentMapper.toApartment(request);
        apartmentUpdated.setToken(token);
        apartmentUpdated.setId(apartment.getId());
        apartmentUpdated.setUnit(apartment.getUnit());
        apartmentUpdated.setNumber(apartment.getNumber());

        Apartment apartmentSaved = apartmentService.save(apartmentUpdated);
        ApartmentResponse response = apartmentMapper.toApartmentResponse(apartmentSaved);

        return ResponseEntity.ok(response);
    }
}