package com.cit.backend.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ContactCondominiumRequest {
    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotBlank(message = "Value cannot be blank")
    private String value;
}
