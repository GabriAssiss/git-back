package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.Condominium;
import com.cit.backend.domain.entity.Employee;
import com.cit.backend.domain.entity.Profile;
import com.cit.backend.domain.entity.Resident;
import com.cit.backend.domain.entity.enums.ProfilePermissions;
import com.cit.backend.domain.repository.EmployeeRepository;
import com.cit.backend.exceptions.UniqueColumnAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProfileService profileService;

    public Employee save(Employee employee) { return employeeRepository.save(employee); }

    public Employee saveAdmin(Employee employee) {
        if (employeeRepository.findByCpf(employee.getCpf()).isPresent()) {
            throw new UniqueColumnAlreadyExistsException("CPF has already been registered");
        }

        Profile profile = employee.getProfile();
        profile.setPermissions(Set.of(ProfilePermissions.ROLE_ADMIN));
        profileService.save(profile);

        employee.setProfile(profile);
        employee.setRole(ProfilePermissions.ROLE_ADMIN.toString());
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResolutionException("User not found with id :" + id));
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findByProfile(Profile profile) {
        return employeeRepository.findByProfile(profile).orElse(null);
    }

    public Employee findUserinfoByProfile(Profile profile) {
        return employeeRepository.findByProfile(profile).orElseThrow(() -> new ResolutionException("User not found"));
    }
}
