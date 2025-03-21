package com.cit.backend.api.response;

import com.cit.backend.api.request.ContactRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PeopleResponse {
    private String name;
    private String cpf;
    private ContactRequest contact;

    public PeopleResponse(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }
}