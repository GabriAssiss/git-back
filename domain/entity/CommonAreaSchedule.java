package com.cit.backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "common_area_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonAreaSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalTime timeStart;

    @Column(nullable = false)
    private LocalTime timeEnd;

    @ElementCollection
    @CollectionTable(name = "common_area_schedule_day_of_week", joinColumns = @JoinColumn(name = "common_areas_schedule_id"))
    private Set<DayOfWeek> dayOfWeek;

    @ManyToOne
    @JoinColumn(name="common_area_id", nullable=false)
    private CommonArea commonArea;
}
