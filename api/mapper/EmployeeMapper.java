package com.cit.backend.api.mapper;

import com.cit.backend.api.request.EmployeeRequest;
import com.cit.backend.domain.entity.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cit.backend.api.request.AdminRequest;
import com.cit.backend.api.response.AdminResponse;
import com.cit.backend.api.response.EmployeeResponse;
import com.cit.backend.domain.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProfileMapper profileMapper;

    public Employee toEmployee(EmployeeRequest request) {
        Employee employee = modelMapper.map(request, Employee.class);
        Profile profile = profileMapper.toProfile(request.getProfile());
        employee.setProfile(profile);
        return employee;
    }

    public Employee toEmployee(AdminRequest request) {
        Employee employee = modelMapper.map(request, Employee.class);
        Profile profile = profileMapper.toProfile(request.getProfile());
        employee.setProfile(profile);
        return employee;
    }

    public AdminResponse toAdminResponse(Employee employee) {
        return modelMapper.map(employee, AdminResponse.class);
    }

    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return modelMapper.map(employee, EmployeeResponse.class);
    }

    public List<EmployeeResponse> toEmployeeResponseAll(List<Employee> employees) {
        return employees.stream()
                .map(this::toEmployeeResponse)
                .collect(Collectors.toList());
    }

    public AdminRequest toAdminRequest(Employee employee) {
        return modelMapper.map(employee, AdminRequest.class);
    }
}
