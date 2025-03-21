package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.Vehicle;
import com.cit.backend.domain.repository.VehicleRepository;
import com.cit.backend.exceptions.UniqueColumnAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle findByPlate(String plate) {
        return vehicleRepository.findByPlate(plate).orElse(null);
    }

    public Vehicle save(Vehicle vehicle) {
        vehicle.setPlate(vehicle.getPlate().replace("-", ""));
        if (this.findByPlate(vehicle.getPlate()) != null) {
            throw new UniqueColumnAlreadyExistsException("Plate already registered");
        }
        return vehicleRepository.save(vehicle);
    }

    public Set<Vehicle> save(Collection<Vehicle> vehicles) {
        return vehicles.stream()
                .map(this::save)
                .collect(java.util.stream.Collectors.toSet());
    }
}
