package com.cit.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "blocks")
@Setter
@Getter
@NoArgsConstructor
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 80)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="condominium_id", nullable=false)
    private Condominium condominium;

    @OneToMany(mappedBy="block", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Unit> units = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name = "block_warning",
            joinColumns = @JoinColumn(name = "block_id"),
            inverseJoinColumns = @JoinColumn(name = "warning_id"))
    private Set<Warning> warnings;
}
