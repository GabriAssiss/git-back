package com.cit.backend.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PeopleRequest {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 80, message = "Name must have between 3 and 80 characters")
    private String name;

    @NotBlank(message = "CPF is mandatory")
    @CPF(message = "Invalid CPF")
    private String cpf;

    private ContactRequest contact;

    private ProfileRequest profile;
}
