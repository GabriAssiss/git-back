package com.cit.backend.api.controller;

import com.cit.backend.api.mapper.EmployeeMapper;
import com.cit.backend.api.request.AdminRequest;
import com.cit.backend.api.request.EmployeeRequest;
import com.cit.backend.api.response.EmployeeResponse;
import com.cit.backend.domain.entity.Employee;
import com.cit.backend.domain.entity.Resident;
import com.cit.backend.domain.service.CondominiumService;
import com.cit.backend.domain.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        EmployeeResponse response = employeeMapper.toEmployeeResponse(employee);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {
        List<Employee> employees = employeeService.findAll();
        List<EmployeeResponse> response = employeeMapper.toEmployeeResponseAll(employees);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
  
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        Employee employee = employeeMapper.toEmployee(request);
        Employee employeeSaved = employeeService.save(employee);
        EmployeeResponse response = employeeMapper.toEmployeeResponse(employeeSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> createAdmin(@Valid @RequestBody AdminRequest request) {

        Employee employee = employeeMapper.toEmployee(request);
        Employee employeeSaved = employeeService.saveAdmin(employee);
        EmployeeResponse response = employeeMapper.toEmployeeResponse(employeeSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
