package com.cit.backend.domain.entity;

import com.cit.backend.domain.entity.enums.TypeVehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 7, nullable = false, unique = true)
    private String plate;

    @Column(length = 80, nullable = false)
    private String model;

    @Column(length = 80, nullable = false)
    private String color;

    @Column(length = 80, nullable = false)
    private String brand;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeVehicle type;

    @ManyToOne
    @JoinColumn(name="apartment_id", nullable=true)
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name="visitant_id", nullable=true)
    private Visitant visitant;

    @PrePersist
    @PreUpdate
    private void validateAssociations() {
        if (this.apartment == null && this.visitant == null) {
            throw new IllegalStateException("Vehicle must be associated with either an apartment or a visitant.");
        }
        if (this.apartment != null && this.visitant != null) {
            throw new IllegalStateException("Vehicle cannot be associated with both an apartment and a visitant.");
        }
    }

}
