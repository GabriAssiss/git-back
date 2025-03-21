package com.cit.backend.domain.entity;

import com.cit.backend.domain.entity.enums.ContactsType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Contacts_value")
@Setter
@Getter
@NoArgsConstructor
public class ContactValue {
        @Id
        private Long id;

        @Column(length = 80, nullable = false)
        private String value;

        @Column(length = 20,nullable = false)
        private ContactsType type;

        @ManyToOne(cascade = CascadeType.ALL)
        private ContactsCondominium contacts;
}
