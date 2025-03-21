package com.cit.backend.domain.entity;

import com.cit.backend.exceptions.InvalidRangeOfTimeException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "reserves")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime timeStart;

    @Column(nullable = false)
    private LocalTime timeEnd;


    @ManyToOne
    @JoinColumn(name="apartment_id", nullable=false)
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name="common_area_id", nullable=false)
    private CommonArea commonArea;

    @PreUpdate
    @PrePersist
    public void validate() {
        if (timeStart.isAfter(timeEnd)) {
            throw new InvalidRangeOfTimeException("The start time must be before the end time");
        }
    }
}
