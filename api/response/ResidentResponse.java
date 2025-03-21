package com.cit.backend.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentResponse {
    private Long id;
    private Long profileId;
    private Long apartmentId;
    private String name;
    private String cpf;
}
