package com.cit.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "units")
@Setter
@Getter
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private int number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="block_id", nullable=false)
    private Block block;

    @OneToMany(mappedBy="unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Apartment> apartments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "unit_warning",
            joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "warning_id"))
    private Set<Warning> warnings;
}
