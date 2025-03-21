package com.cit.backend.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ContactsCondominiumRequest {
    @NotBlank(message = "Location cannot be blank")
    private String location;

    private ContactCondominiumRequest[] contacts;
}
