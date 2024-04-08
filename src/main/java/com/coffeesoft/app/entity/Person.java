package com.coffeesoft.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "cell_phone")
    protected String cellPhone;

    @Column(name = "document")
    private String document;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "account_id", referencedColumnName = "id_account")
    private Account account;
}