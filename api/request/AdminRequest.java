package com.cit.backend.api.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminRequest extends PeopleRequest {
    public AdminRequest(String name, String cpf, ContactRequest contact, ProfileRequest profile) {
        super(name, cpf, contact, profile);
    }
}
