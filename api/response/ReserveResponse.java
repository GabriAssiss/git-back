package com.cit.backend.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveResponse {
    private Long id;
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Long commonAreaId;
}
