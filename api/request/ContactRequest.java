package com.cit.backend.api.request;

import com.cit.backend.api.validator.Phone;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {
    @Phone(message = "Invalid phone")
    private String phone;

    @Email(message = "Invalid email")
    private String email;
}
