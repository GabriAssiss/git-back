package com.cit.backend.domain.repository;
import com.cit.backend.domain.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PeopleRepository<Employee>{}
