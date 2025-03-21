package com.cit.backend.api.mapper;

import com.cit.backend.api.request.ApartmentRequest;
import com.cit.backend.api.response.ApartmentResponse;
import com.cit.backend.domain.entity.Apartment;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApartmentMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private ResidentMapper residentMapper;

    public Apartment toApartment(@Valid ApartmentRequest request) {
        Apartment apartment = modelMapper.map(request, Apartment.class);
        apartment.setResidents(residentMapper.toResident(request.getResidents()));
        apartment.setVehicles(vehicleMapper.toVehicle(request.getVehicles()));
        System.out.println(apartment.getVehicles().size());
        return apartment;
    }

    public ApartmentResponse toApartmentResponse(Apartment apartment) {
        ApartmentResponse response = modelMapper.map(apartment, ApartmentResponse.class);
        response.setResidents(residentMapper.toResidentResponse(apartment.getResidents()));
        response.setVehicles(vehicleMapper.toVehicleResponse(apartment.getVehicles()));
        return response;
    }

    public List<ApartmentResponse> toApartmentResponseList(List<Apartment> apartments) {
        return apartments.stream()
                .map(this::toApartmentResponse)
                .collect(Collectors.toList());
    }

    public ApartmentRequest toApartmentRequest(Apartment apartment) {
        return modelMapper.map(apartment, ApartmentRequest.class);
    }

    public List<ApartmentRequest> toApartmentRequestList(List<Apartment> apartments) {
        return apartments.stream()
                .map(this::toApartmentRequest)
                .collect(Collectors.toList());
    }
}
