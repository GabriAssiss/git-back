package com.cit.backend.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CondominiumResponse {
    private Long id;
    private String name;
    private String cnpj;
    private int blocks;
    private int units;
    private int apartments;
    private long managerId;
    private AddressResponse address;
}
