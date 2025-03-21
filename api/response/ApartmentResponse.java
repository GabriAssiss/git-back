package com.cit.backend.api.response;

import com.cit.backend.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentResponse {
    private Long id;
    private int number;
    private String token;
    private Long unitId;
    private Set<Warning> warnings;
    private Set<Visitant> visitants;
    private Set<ResidentResponse> residents;
    private Set<Deliveries> deliveries;
    private Set<VehicleResponse> vehicles;
    private Set<Billet> billets;
    private Set<Ticket> tickets;
}
