package com.cit.backend.api.request;

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
public class OmbudsmenRequest {
    private Long id;
    private TypeTicket type;
    private String title;
    private String description;
    private StatusTicket status;
    private String response;
    private Long employeeId;
}
