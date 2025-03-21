package com.cit.backend.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "condominiums")
@Setter
@Getter
@NoArgsConstructor
public class Condominium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 80, nullable = false)
    private String name;

    @Unsigned
    private int blocks;

    @Unsigned
    private int units;

    @Unsigned
    private int floors;

    @Unsigned
    private int apartments;

    @Column(length = 18, unique = true)
    private String cnpj;

    @OneToOne(mappedBy = "condominium", cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy="condominium", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy="condominium", cascade = CascadeType.ALL)
    private Set<Block> blockList = new HashSet<>();

    @OneToMany(mappedBy = "condominium", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Warning> warning = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "condominium_warning",
            joinColumns = @JoinColumn(name = "condominium_id"),
            inverseJoinColumns = @JoinColumn(name = "warning_id"))
    private Set<Warning> warnings = new HashSet<>();

    @OneToMany(mappedBy = "condominium", cascade = CascadeType.ALL)
    private Set<CommonArea> commonAreas = new HashSet<>();

    @OneToMany(mappedBy = "condominium", cascade = CascadeType.ALL)
    private Set<LostAndFound> lostAndFound = new HashSet<>();

    @OneToMany(mappedBy = "condominium", cascade = CascadeType.ALL)
    private Set<Meeting> meeting = new HashSet<>();

    @OneToMany(mappedBy = "condominium", cascade = CascadeType.ALL)
    private Set<Rules> rules = new HashSet<>();

    @OneToMany(mappedBy = "condominium", cascade = CascadeType.ALL)
    private Set<Income> income = new HashSet<>();

    @OneToMany(mappedBy = "condominium", cascade = CascadeType.ALL)
    private Set<ContactsCondominium> contactsCondominium = new HashSet<>();
}
