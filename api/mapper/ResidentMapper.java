package com.cit.backend.api.mapper;

import com.cit.backend.api.request.ResidentRequest;
import com.cit.backend.api.response.ResidentResponse;
import com.cit.backend.domain.entity.Resident;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ResidentMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Resident toResident(ResidentRequest request) {
        return modelMapper.map(request, Resident.class);
    }

    public Set<Resident> toResident(Collection<ResidentRequest> requests) {
        return requests.stream()
                .map(this::toResident)
                .collect(Collectors.toSet());
    }

    public ResidentResponse toResidentResponse(Resident resident) {
        ResidentResponse response = modelMapper.map(resident, ResidentResponse.class);
        if (resident.getProfile() != null) response.setProfileId(resident.getProfile().getId());
        response.setApartmentId(resident.getApartment().getId());
        return response;
    }

    public Set<ResidentResponse> toResidentResponse(Collection<Resident> residents) {
        return residents.stream()
                .map(this::toResidentResponse)
                .collect(Collectors.toSet());
    }
}
