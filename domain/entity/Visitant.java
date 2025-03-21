package com.cit.backend.domain.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "visitants")
public class Visitant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80, nullable = false)
    private String name;

    @Column(length = 11, unique = true, nullable = false)
    private String cpf;

    @ManyToOne
    @JoinColumn(name="apartment_id", nullable=false)
    private Apartment apartment;

    @OneToMany(mappedBy = "visitant")
    private Set<Vehicle> vehicle;

}
