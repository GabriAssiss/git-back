package com.cit.backend.domain.entity;

import com.cit.backend.domain.entity.enums.TypeBillet;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;

import java.time.LocalDate;

@Entity(name = "billets ")
public class Billet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TypeBillet type;

    @Unsigned
    private float value;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate dueDate;

    private boolean paid;

    @ManyToOne
    @JoinColumn(name="apartment_id", nullable=false)
    private Apartment apartment;
}
