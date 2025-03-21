package com.cit.backend.api.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class ApartmentRequest {
    @Valid
    private Set<ResidentRequest> residents;
    private Set<VehicleRequest> vehicles;
}
