package com.cit.backend.api.request;

import com.cit.backend.domain.entity.Apartment;
import com.cit.backend.domain.entity.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorRequest {

    @NotBlank(message = "Name can't be null.")
    private String name;

    @NotBlank(message = "CPF can't be null.")
    @CPF(message = "Invalid CPF")
    private String cpf;

    @NotBlank(message = "Apartment can't be null.")
    private Apartment apartment;

    private Set<Vehicle> vehicle;

}
