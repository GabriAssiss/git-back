package com.cit.backend.api.response;

import com.cit.backend.domain.entity.enums.StatusTicket;
import com.cit.backend.domain.entity.enums.TypeTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OmbudsmenResponse {
    private Long id;
    private TypeTicket type;
    private String title;
    private String description;
    private StatusTicket status;
    private String response;
    private Long apartmentId;
    private Long employeeId;
}
