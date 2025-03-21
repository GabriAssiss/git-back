package com.cit.backend.api.controller;

import com.cit.backend.api.request.*;
import com.cit.backend.api.response.CondominiumResponse;
import com.cit.backend.api.response.EmployeeResponse;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generator")
public class GenereteController {

    @Autowired
    private Faker faker;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private CondominiumController condominiumController;

    @GetMapping
    public ResponseEntity<List<Object>> generate() {
        List<Object> condominiums = new java.util.ArrayList<>(List.of());

        for (int i = 0; i < 10; i++) {
            condominiums.add(genereteCondominium());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(condominiums);
    }

    private CondominiumResponse genereteCondominium() {
        // numeros pequenos para não sobrecarregar a aplicação
        int blocks = 4;
        int units = 4;
        int floors = 4;
        int apartments = 4;

        EmployeeResponse admin = genereteAdmin();
        AddressRequest address = new AddressRequest(
                faker.address().streetAddress(),
                Integer.parseInt(faker.address().buildingNumber()),
                faker.address().secondaryAddress(),
                faker.address().city(),
                "GS",
                faker.regexify("\\d{8}")
        );
        CondominiumRequest condominium = new CondominiumRequest(
            faker.company().name(),
            faker.regexify("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}"),
                blocks, units, floors, apartments,
            admin.getId(),
            address
        );
        ResponseEntity<CondominiumResponse> response = condominiumController.createCondominium(condominium);
        return response.getBody();
    }
/*
    private EmployeeResponse generateEmployee(){
        //ContactRequest employeeContact = new ContactRequest(faker.phoneNumber().cellPhone(), faker.internet().emailAddress());
        ProfileRequest employeeProfile = new ProfileRequest(
                faker.internet().emailAddress(),
                faker.internet().password(),
                null);

        EmployeeRequest employee = new EmployeeRequest(
                faker.name().fullName(),
                faker.idNumber().valid(),
                null,
                employeeProfile
        );
        ResponseEntity<EmployeeResponse> response = employeeController.(employee);
        return response.getBody();
    }
*/
    private EmployeeResponse genereteAdmin() {
        //ContactRequest adminContact = new ContactRequest(faker.phoneNumber().cellPhone(), faker.internet().emailAddress());
        ProfileRequest adminProfile = new ProfileRequest(
                faker.internet().emailAddress(),
                faker.internet().password(),
                null);

        AdminRequest admin = new AdminRequest(
                faker.name().fullName(),
                faker.idNumber().valid(),
                null,
                adminProfile
        );
        ResponseEntity<EmployeeResponse> response = employeeController.createAdmin(admin);
        return response.getBody();
    }
}
