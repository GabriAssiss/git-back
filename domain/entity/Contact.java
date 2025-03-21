package com.cit.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "contacts")
@Setter
@Getter
@NoArgsConstructor
public class Contact {

    @Id
    @Column(name = "people_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "people_id")
    private People people;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 11, unique = true)
    private String phone;
}
