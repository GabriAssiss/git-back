package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.*;
import com.cit.backend.domain.repository.*;
import com.cit.backend.exceptions.UniqueColumnAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CondominiumService {
    @Autowired
    private CondominiumRepository condominiumRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private ContactsCondominiumRepository contactsCondominiumRepository;

    public Condominium save(Condominium condominium) {
        if (condominiumRepository.findByCnpj(condominium.getCnpj()) != null) {
            throw new UniqueColumnAlreadyExistsException("CNPJ has already been registered");
        }

        if (condominiumRepository.findByManagerId(condominium.getManager().getId()) != null) {
            throw new UniqueColumnAlreadyExistsException("Syndico is already managing a condominium");
        }

        condominium = condominiumRepository.save(condominium);
        condominium.getManager().setCondominium(condominium);

        System.out.println(condominium.getManager().getId());
        System.out.println(condominium.getManager().getName());
        int UNIT = 1;

        for (int i = 0; i < condominium.getBlocks(); i++) {
            Block block = new Block();
            block.setName("Block " + (i + 1));

            for (int j = 0; j < condominium.getUnits(); j++) {
                UNIT++;
                Unit unit = new Unit();
                unit.setNumber(UNIT);

                for (int k = 0; k < condominium.getFloors(); k++) {
                    int floor = k + 1;
                    for (int l = 0; l < condominium.getApartments(); l++) {
                        // TODO Melhora a geração de token
                        Apartment apartment = new Apartment();
                        apartment.setUnit(unit);
                        apartment.setNumber((floor * 100) + l + 1);
                        apartment.setToken(tokenService.generateToken());
                        unit.getApartments().add(apartment);
                    }
                    unit.setBlock(block);
                }
                block.getUnits().add(unit);
            }
            block.setCondominium(condominium);
            condominium.getBlockList().add(block);
        }
        return condominiumRepository.save(condominium);
    }

    public Condominium findById(Long id) {
        return condominiumRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        condominiumRepository.deleteById(id);
    }

    public Condominium update(Condominium condominium) {
        return condominiumRepository.save(condominium);
    }

    public ContactsCondominium saveContact(ContactsCondominium contactsCondominium) {
        return contactsCondominiumRepository.save(contactsCondominium);
    }
}
