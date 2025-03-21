package com.cit.backend.api.controller;

import com.cit.backend.api.mapper.AccountabilityMapper;
import com.cit.backend.api.request.AccountabilityRequest;
import com.cit.backend.api.response.AccountabilityResponse;
import com.cit.backend.domain.entity.*;
import com.cit.backend.domain.repository.AccountabilityRepository;
import com.cit.backend.domain.service.AccountabilityService;
import com.cit.backend.domain.service.EmployeeService;
import com.cit.backend.domain.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accountability")
public class AccountabilityController {
    @Autowired
    private AccountabilityService accountabilityService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResidentService residentService;
    @Autowired
    private AccountabilityRepository accountabilityRepository;
    @Autowired
    private AccountabilityMapper accountabilityMapper;

    @GetMapping
    public ResponseEntity<List<AccountabilityResponse>> getAccountability() {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeService.findByProfile(profile);
        Resident resident = residentService.getFromProfile(profile);
        Condominium condominium = null;

        if (employee != null) {
            condominium = employee.getCondominium();
        }
        else if (resident != null) {
            condominium = resident.getApartment().getUnit().getBlock().getCondominium();
        }
        else{
            throw new RuntimeException("Não foi possivel encontraar o condominio vinculado ao usuario logado!");
        }

        if (condominium == null) {
            throw new RuntimeException("Condominio não encontrado!");
        }

        List<Income> incomes = accountabilityService.findAllByCondominium(condominium);

        List<AccountabilityResponse> response = accountabilityMapper.toAccountabilityResponse(incomes);
        return ResponseEntity.ok(response);
    }

    // TODO - Salvar arquivo
    @PostMapping
    public ResponseEntity<AccountabilityResponse> create(@RequestBody AccountabilityRequest request){
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeService.findByProfile(profile);

        if (employee == null) {
            throw new RuntimeException("Usuario não identificado!");
        }

        Income income = accountabilityMapper.toIncome(request);

        income = accountabilityService.save(income);
        AccountabilityResponse accountabilityResponse = accountabilityMapper.toAccountabilityResponse(income);
        return ResponseEntity.ok(accountabilityResponse);
    }
}
