package com.cit.backend.api.request;

import com.cit.backend.domain.entity.Condominium;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LostAndFoundRequest {

    private Long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotNull(message = "arrival is mandatory")
    private LocalDate arrival;

    @NotNull(message = "departure is mandatory")
    private LocalDate departure;

    @NotBlank(message = "description is mandatory")
    @Size(min = 10, max = 300, message = "Description must be between 10 and 300 characters")
    private String description;

    @NotBlank(message = "location is mandatory")
    private String location;

}
