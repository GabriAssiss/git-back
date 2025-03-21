package com.cit.backend.domain.entity;

import com.cit.backend.domain.entity.enums.TypeContactCondominium;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "contact_condominium")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactCondominium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String value;

    @Column
    private TypeContactCondominium type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contacts_condominium_id", nullable = false)
    private ContactsCondominium contactsCondominium;
}
