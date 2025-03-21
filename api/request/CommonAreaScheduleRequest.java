package com.cit.backend.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonAreaScheduleRequest {
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Set<DayOfWeek> dayOfWeek;
}
