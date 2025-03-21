package com.cit.backend.api.mapper;

import com.cit.backend.api.request.CondominiumRequest;
import com.cit.backend.api.response.CondominiumResponse;
import com.cit.backend.domain.entity.Address;
import com.cit.backend.domain.entity.Condominium;
import com.cit.backend.domain.entity.Employee;
import com.cit.backend.domain.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CondominiumMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeService employeeService;

    public Condominium toCondominium(CondominiumRequest condominium) {
        Address address = modelMapper.map(condominium.getAddress(), Address.class);
        Condominium condominiumEntity = modelMapper.map(condominium, Condominium.class);

        address.setCondominium(condominiumEntity);
        condominiumEntity.setAddress(address);

        return condominiumEntity;
    }

    public CondominiumResponse toCondominiumResponse(Condominium condominium) {
        return modelMapper.map(condominium, CondominiumResponse.class);
    }

    public CondominiumRequest toCondominiumRequest(Condominium condominium) {
        return modelMapper.map(condominium, CondominiumRequest.class);
    }
}
