package com.cit.backend.api.request;

import com.cit.backend.domain.entity.enums.TypeVehicle;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
    @NotBlank(message = "Type is mandatory")
    @Enumerated(EnumType.STRING)
    private TypeVehicle type;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @NotBlank(message = "Color is mandatory")
    private String color;

    @NotBlank(message = "Plate is mandatory")
    @Size(min = 6, max = 7, message = "Plate must be between 6 and 7 characters")
    private String plate;
}
