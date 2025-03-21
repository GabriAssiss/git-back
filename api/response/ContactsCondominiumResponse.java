package com.cit.backend.api.response;

import com.cit.backend.domain.entity.ContactsCondominium;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactsCondominiumResponse {
    private String location;
    private ContactCondominiumResponse[] contacts;
    public ContactsCondominiumResponse(ContactsCondominium contact) {
    }
}
