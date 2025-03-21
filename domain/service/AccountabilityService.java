package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.Condominium;
import com.cit.backend.domain.entity.Income;
import com.cit.backend.domain.repository.AccountabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountabilityService {
    @Autowired
    private AccountabilityRepository accountabilityRepository;

    public Income save(Income income) {
        return accountabilityRepository.save(income);
    }

    public List<Income> findAllByCondominium(Condominium condominium) {
        return accountabilityRepository.findAllByCondominium(condominium);
    }
}
