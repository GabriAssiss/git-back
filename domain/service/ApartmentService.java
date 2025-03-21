package com.cit.backend.domain.service;

import com.cit.backend.api.validator.JWTToken;
import com.cit.backend.domain.entity.Apartment;
import com.cit.backend.domain.entity.Resident;
import com.cit.backend.domain.entity.Vehicle;
import com.cit.backend.domain.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private VehicleService vehicleService;

    public Apartment save(Apartment apartment) {
        Set<Resident> residents = apartment.getResidents();
        residents.forEach(resident -> resident.setApartment(apartment));
        apartment.setResidents(residentService.save(residents));

        Set<Vehicle> vehicles = apartment.getVehicles();
        vehicles.forEach(vehicle -> vehicle.setApartment(apartment));
        apartment.setVehicles(vehicleService.save(apartment.getVehicles()));

        return apartmentRepository.save(apartment);
    }

    public Apartment findById(Long id) {
        return apartmentRepository.findById(id).orElse(null);
    }

    public Apartment findByToken(@JWTToken String token) {
        return apartmentRepository.findByToken(token).orElse(null);
    }

    public List<Apartment> findAllByUnitId(Long unitId) {
        return apartmentRepository.findAllByUnitId(unitId);
    }

    public void deleteById(Long id) {
        apartmentRepository.deleteById(id);
    }

    public Apartment update(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    public Apartment findByResident(Resident resident) {
        return apartmentRepository.findByResidentsId(resident.getId()).orElse(null);
    }
}

