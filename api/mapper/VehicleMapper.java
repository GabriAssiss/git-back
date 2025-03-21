package com.cit.backend.api.mapper;

import com.cit.backend.api.request.VehicleRequest;
import com.cit.backend.api.response.VehicleResponse;
import com.cit.backend.domain.entity.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VehicleMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Vehicle toVehicle(VehicleRequest request) {
        request.setPlate(request.getPlate().replace("-", ""));
        return modelMapper.map(request, Vehicle.class);
    }

    public Set<Vehicle> toVehicle(Collection<VehicleRequest> requests) {
        return requests.stream()
                .map(this::toVehicle)
                .collect(Collectors.toSet());
    }

    public VehicleResponse toVehicleResponse(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleResponse.class);
    }

    public Set<VehicleResponse> toVehicleResponse(Collection<Vehicle> vehicles) {
        return vehicles.stream()
                .map(this::toVehicleResponse)
                .collect(Collectors.toSet());
    }
}
