package com.cit.backend.api.request;

import com.cit.backend.api.validator.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Phone
    private String phone;

    @NotBlank(message = "CPF is mandatory")
    @CPF
    private String cpf;

    @Email
    private String email;

    private ProfileRequest profile;
}
