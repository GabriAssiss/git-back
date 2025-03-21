package com.cit.backend.api.request;

import com.cit.backend.api.validator.Phone;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    private ProfileRequest profile;

    @NotBlank(message = "Phone is mandatory")
    @Phone
    private String phone;

    @NotBlank(message = "Role is mandatory")
    @Size(min = 3, max = 50, message = "Role must be between 3 and 50 characters")
    private String role;

    private VehicleRequest vehicle;
}
