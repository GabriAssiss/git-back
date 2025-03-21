package com.cit.backend.api.response;

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
public class LostAndFoundResponse {
    private Long id;
    private String name;
    private LocalDate arrival;
    private LocalDate departure;
    private String description;
    private String location;
}