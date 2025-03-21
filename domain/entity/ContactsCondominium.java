package com.cit.backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity(name = "contacts_condominium")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactsCondominium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String location;

    @OneToMany(mappedBy = "contactsCondominium")
    private Set<ContactCondominium> contacts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condominium_id", nullable = false)
    private Condominium condominium;
}
