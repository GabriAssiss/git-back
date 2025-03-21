package com.cit.backend.api.request;

import com.cit.backend.api.validator.ZipCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AddressRequest {

    @NotBlank(message = "Street is mandatory")
    @Size(min = 3, max = 80, message = "Street must have between 3 and 80 characters")
    private String street;

    @NotBlank(message = "Number is mandatory")
    @PositiveOrZero(message = "Number must be greater than zero")
    private int number;

    @NotBlank(message = "Neighborhood is mandatory")
    @Size(min = 3, max = 20, message = "Neighborhood must have between 3 and 20 characters")
    private String neighborhood;

    @NotBlank(message = "City is mandatory")
    @Size(min = 3, max = 30, message = "City must have between 3 and 30 characters")
    private String city;

    @NotBlank(message = "State is mandatory")
    @Size(min = 2, max = 2, message = "State must have 2 characters")
    private String state;

    @NotBlank(message = "ZipCode is mandatory")
    @Size(min = 9, max = 9, message = "ZipCode must have 8 characters")
    @ZipCode(message = "Invalid zip code")
    private String zipCode;
}
