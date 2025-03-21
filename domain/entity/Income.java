package com.cit.backend.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Timestamp DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="comdominium_id", nullable=false)
    private Condominium condominium;
}
