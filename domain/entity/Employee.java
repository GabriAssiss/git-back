package com.cit.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "employees")
@Setter
@Getter
@NoArgsConstructor
public class Employee extends People {
    @Column(length = 80)
    private String role;

    @OneToOne(mappedBy = "manager", fetch = FetchType.LAZY)
    private Condominium management;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="condominium_id", nullable=true)
    private Condominium condominium;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Ticket> tickets;
}
