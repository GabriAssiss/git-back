package com.cit.backend.domain.entity;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "addresses")
public class Address {

    @Id
    @Column(name = "condominium_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "condominium_id")
    private Condominium condominium;

    @Column(length = 80)
    private String street;

    @Unsigned
    private int number;

    @Column(length = 20)
    private String neighborhood;

    @Column(length = 30)
    private String city;

    @Column(length = 2)
    private String state;

    @Column(length = 9)
    private String zipCode;
}
