package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.Apartment;
import com.cit.backend.domain.entity.Condominium;
import com.cit.backend.domain.entity.Ticket;
import com.cit.backend.domain.repository.OmbudsmanRepository;
import com.cit.backend.exceptions.UniqueColumnAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OmbudsmanService {
    @Autowired
    private OmbudsmanRepository ombudsmanRepository;

    public Ticket findById(Long id) {
        return ombudsmanRepository.findById(id).orElse(null);
    }

    public Ticket create(Ticket ticket) {
        if (ticket.getId() != null && ombudsmanRepository.findById(ticket.getId()).isPresent()) {
            throw new UniqueColumnAlreadyExistsException("Ticket has already been registered");
        }

        return ombudsmanRepository.save(ticket);
    }

    public Ticket update(Ticket ticket) {
        return ombudsmanRepository.save(ticket);
    }

    public List<Ticket> findAllByApartment(Apartment apartment) {
        return ombudsmanRepository.findAllByApartment(apartment);
    }

    public List<Ticket> findAllByCondominium(Condominium condominium) {
        Set<Apartment> apartments = new HashSet<>();
        condominium.getBlockList().forEach(block -> {
            block.getUnits().forEach(unit -> {
                apartments.addAll(unit.getApartments());
            });
        });
        return ombudsmanRepository.findAllByApartmentIn(apartments);
    }
}
