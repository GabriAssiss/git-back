package com.cit.backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity(name = "lost_and_found")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LostAndFound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate arrival;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate departure;

    @Column(length = 180, nullable = false)
    private String description;

    @Column(length = 100, nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name="comdominium_id", nullable=false)
    private Condominium condominium;
}
