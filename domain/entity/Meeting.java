package com.cit.backend.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "meetings")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="comdominum_id", nullable=false)
    private Condominium condominium;
}
