package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.*;
import com.cit.backend.domain.repository.DeliveriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveriesService {
    @Autowired
    DeliveriesRepository deliveriesRepository;

    public Deliveries save(Deliveries deliveries) {
        return deliveriesRepository.save(deliveries);
    }

    public List<Deliveries> findByResident(Resident resident) {
        return deliveriesRepository.findAllByApartment(resident.getApartment());
    }

    public List<Deliveries> findByEmployee(Employee employee) {
        ArrayList<Deliveries> deliveries = new ArrayList<>();
        for(Block block : employee.getCondominium().getBlockList()) {
            for(Unit unit : block.getUnits()) {
                for(Apartment apartment : unit.getApartments()) {
                    deliveries.addAll(deliveriesRepository.findAllByApartment(apartment));
                }
            }
        }
        return deliveries.stream().toList();
    }
}
