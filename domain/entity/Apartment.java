package com.cit.backend.domain.entity;

import com.cit.backend.api.validator.JWTToken;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity(name = "apartments")
@Setter
@Getter
@NoArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "apartment_seq", sequenceName = "apartment_id_seq", allocationSize = 1)
    private Long id;

    private int number;

    @Column(unique = true)
    @JWTToken
    private String token;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id", nullable=false)
    private Unit unit;

    @ManyToMany
    @JoinTable(
            name = "apartment_warning",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "warning_id"))
    private Set<Warning> warnings;

    @OneToMany(mappedBy = "apartment")
    private Set<Visitant> visits;

    @OneToMany(mappedBy = "apartment")
    private Set<Resident> residents;

    @OneToMany(mappedBy = "apartment")
    private Set<Deliveries> deliveries;

    @OneToMany(mappedBy = "apartment")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "apartment")
    private Set<Billet> billets;

    @OneToMany(mappedBy = "apartment")
    private Set<Ticket> tickets;

}
