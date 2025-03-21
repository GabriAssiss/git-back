package com.cit.backend.api.response;

import com.cit.backend.domain.entity.Apartment;
import com.cit.backend.domain.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorResponse {

    private Long id;
    private String nome;
    private String cpf;
    private Apartment apartment;
    private Set<Vehicle> vehicles;

}
