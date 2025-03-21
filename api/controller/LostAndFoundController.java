package com.cit.backend.api.controller;

import com.cit.backend.api.mapper.LostAndFoundMapper;
import com.cit.backend.api.request.CommonAreaRequest;
import com.cit.backend.api.request.LostAndFoundRequest;
import com.cit.backend.api.response.CommonAreaResponse;
import com.cit.backend.api.response.LostAndFoundResponse;
import com.cit.backend.domain.entity.CommonArea;
import com.cit.backend.domain.entity.Employee;
import com.cit.backend.domain.entity.LostAndFound;
import com.cit.backend.domain.entity.Profile;
import com.cit.backend.domain.service.EmployeeService;
import com.cit.backend.domain.service.LostAndFoundService;
import com.cit.backend.exceptions.MissingVariableException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lost-and-found")
public class LostAndFoundController {

    @Autowired
    private LostAndFoundService lostObjectService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LostAndFoundMapper lostObjectMapper;

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<LostAndFoundResponse> lostObjectCreate(@RequestBody LostAndFoundRequest lostObject) {
        LostAndFound lostAndFound = lostObjectMapper.toLostAndFound(lostObject);
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeService.findByProfile(profile);
        if(employee == null){
            throw new RuntimeException("Employee not found.");
        }
        lostAndFound.setCondominium(employee.getCondominium());
        lostAndFound = lostObjectService.save(lostAndFound);
        LostAndFoundResponse response = lostObjectMapper.toLostAndFoundResponse(lostAndFound);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<LostAndFoundResponse>> getLostObjects() {
        List<LostAndFound> lostObjects;
        lostObjects = lostObjectService.getLostObjects();
        List<LostAndFoundResponse> response = lostObjectMapper.toLostAndFoundResponse(lostObjects);
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<LostAndFoundResponse> updateCommonArea(@RequestBody LostAndFoundRequest request) {
        if(request.getId() == null){
            throw new RuntimeException("An ID is necessary.");
        }
        LostAndFound lostObject = lostObjectMapper.toLostAndFound(request);
        lostObject = lostObjectMapper.fillNullFields(lostObject, lostObjectService.findById(request.getId()));
        lostObject = lostObjectService.save(lostObject);
        LostAndFoundResponse response = lostObjectMapper.toLostAndFoundResponse(lostObject);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id:\\d+}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Void> lostObjectDelete(@PathVariable("id") Long id){
        lostObjectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
