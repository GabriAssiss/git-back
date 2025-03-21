package com.cit.backend.domain.entity;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity(name = "common_areas")
@Getter
@Setter
public class CommonArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Unsigned
    private float tax;

    @ManyToOne
    @JoinColumn(name="condominium_id", nullable=false)
    private Condominium condominium;

    @OneToMany(mappedBy = "commonArea", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<CommonAreaSchedule> schedule;

    @OneToMany(mappedBy = "commonArea", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Reserve> reserves;
}
