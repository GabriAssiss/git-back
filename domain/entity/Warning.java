
package com.cit.backend.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity(name = "warnings")
public class Warning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "condominium_id", nullable = false)
    private Condominium condominium;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @ManyToMany(mappedBy = "warnings")
    private Set<Condominium> condominiums;

    @ManyToMany(mappedBy = "warnings")
    private Set<Block> blocks;

    @ManyToMany(mappedBy = "warnings")
    private Set<Unit> units;

    @ManyToMany(mappedBy = "warnings")
    private Set<Apartment> apartments;

    // Getters and setters
}