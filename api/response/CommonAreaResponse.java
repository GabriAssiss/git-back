package com.cit.backend.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonAreaResponse {
    private Long id;
    private String name;
    private String description;
    private float tax;
    private Long condominiumId;
    private Set<ReserveResponse> reserves;
    private Set<CommonAreaScheduleResponse> schedule;
}
