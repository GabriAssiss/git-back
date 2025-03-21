package com.cit.backend.domain.entity;

import com.cit.backend.domain.entity.enums.StatusTicket;
import com.cit.backend.domain.entity.enums.TypeTicket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private TypeTicket type;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String title;

    @Column
    private StatusTicket status;

    @Column(columnDefinition = "TEXT")
    private String response;

    @ManyToOne
    @JoinColumn(name="apartment_id", nullable=false)
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=true)
    private Employee employee;
}
