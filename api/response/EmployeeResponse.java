package com.cit.backend.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse extends PeopleResponse {
    private int id;
    private String role;

    public EmployeeResponse(String name, String cpf, String role, int id) {
        super(name, cpf);
        this.role = role;
        this.id = id;
    }
}
