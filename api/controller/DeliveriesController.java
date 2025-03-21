package com.cit.backend.api.controller;

import com.cit.backend.api.mapper.DeliveriesMapper;
import com.cit.backend.api.request.DeliveriesRequest;
import com.cit.backend.api.response.DeliveriesResponse;
import com.cit.backend.domain.entity.Deliveries;
import com.cit.backend.domain.entity.Employee;
import com.cit.backend.domain.entity.Profile;
import com.cit.backend.domain.entity.Resident;
import com.cit.backend.domain.service.DeliveriesService;
import com.cit.backend.domain.service.EmployeeService;
import com.cit.backend.domain.service.ResidentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveriesController {
    @Autowired
    private DeliveriesService deliveriesService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DeliveriesMapper deliveriesMapper;

    @GetMapping
    public ResponseEntity<List<DeliveriesResponse>> getDeliveries(){
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Resident resident = residentService.getFromProfile(profile);
        Employee employee = employeeService.findByProfile(profile);

        List<Deliveries> deliveries;
        if (resident != null) deliveries = deliveriesService.findByResident(resident);
        else if(employee != null) deliveries = deliveriesService.findByEmployee(employee);
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        List<DeliveriesResponse> responses = deliveriesMapper.toDeliveriesResponse(deliveries);
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<DeliveriesResponse> createDeliveries(@RequestBody DeliveriesRequest request) {
        Deliveries deliveries = deliveriesMapper.toDeliveries(request);
        Deliveries saved = deliveriesService.save(deliveries);
        DeliveriesResponse response = deliveriesMapper.toDeliveriesResponse(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
