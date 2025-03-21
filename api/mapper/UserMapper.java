package com.cit.backend.api.mapper;

import com.cit.backend.api.response.UserResponse;
import com.cit.backend.domain.entity.Employee;
import com.cit.backend.domain.entity.People;
import com.cit.backend.domain.entity.Resident;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public Object toUserResponse(Employee employee) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(employee.getName());
        userResponse.setCondominium(employee.getCondominium().getName());
        return userResponse;
    }

    public Object toUserResponse(Resident resident) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(resident.getName());
        userResponse.setApartament(resident.getApartment().getNumber());
        userResponse.setUnit(resident.getApartment().getUnit().getNumber());
        userResponse.setBlock(resident.getApartment().getUnit().getBlock().getName());
        userResponse.setCondominium(resident.getApartment().getUnit().getBlock().getCondominium().getName());
        return userResponse;
    }
}
