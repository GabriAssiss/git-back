package com.cit.backend.api.request;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CondominiumRequest {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 80, message = "Name must have between 3 and 80 characters")
    private String name;

    @NotBlank(message = "CNPJ is mandatory")
    @CNPJ(message = "Invalid CNPJ")
    private String cnpj;

    @NotNull(message = "Blocks is mandatory")
    @Positive(message = "Blocks must be greater than zero")
    private int blocks;

    @NotNull(message = "Units is mandatory")
    @Positive(message = "Units must be greater than zero")
    private int units;

    @NotNull(message = "Floors is mandatory")
    @Positive(message = "Floors must be greater than zero")
    private int floors;

    @NotNull(message = "Apartments is mandatory")
    @Positive(message = "Apartments must be greater than zero")
    private int apartments;

    @NotNull(message = "Manager ID is mandatory")
    @Positive(message = "Manager ID must be greater than zero")
    private long managerId;
    
    private AddressRequest address;
}
