package com.cit.backend.api.controller;

import com.cit.backend.api.mapper.UserMapper;
import com.cit.backend.domain.entity.*;
import com.cit.backend.domain.entity.enums.ProfilePermissions;
import com.cit.backend.domain.service.EmployeeService;
import com.cit.backend.domain.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ResidentService residentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<Object> getPeopleByUser() {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object response;
        if (profile.getPermissions().contains(ProfilePermissions.ROLE_RESIDENT)) {
            Resident resident = residentService.findByProfile(profile);
            response = userMapper.toUserResponse(resident);
        } else{
            Employee employee = employeeService.findUserinfoByProfile(profile);
            response = userMapper.toUserResponse(employee);
        }
    return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
