package com.cit.backend.api.controller;
import com.cit.backend.api.mapper.CondominiumMapper;
import com.cit.backend.domain.entity.Condominium;
import com.cit.backend.domain.entity.Employee;
import com.cit.backend.domain.service.CondominiumService;
import com.cit.backend.api.request.CondominiumRequest;
import com.cit.backend.api.response.CondominiumResponse;
import com.cit.backend.domain.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/condominium")
public class CondominiumController {

    @Autowired
    private CondominiumService condominiumService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CondominiumMapper condominiumMapper;

    @PostMapping
    public ResponseEntity<CondominiumResponse> createCondominium(@Valid @RequestBody CondominiumRequest request) {
        Condominium condominium = condominiumMapper.toCondominium(request);

        Employee manager = employeeService.findById(request.getManagerId());
        condominium.setManager(manager);

        Condominium condominiumSaved = condominiumService.save(condominium);
      
        manager = condominiumSaved.getManager();
        manager.setCondominium(condominiumSaved);
        employeeService.save(manager);

        CondominiumResponse response = condominiumMapper.toCondominiumResponse(condominiumSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
