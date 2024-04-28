package com.coffeesoft.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected long id;

    @Column(name = "first_name")
    @NotNull(message = "The First Name can´t is empty")
    protected String firstName;

    @Column(name = "last_name")
    @NotNull(message = "The Last Name can´t is empty")
    protected String lastName;

    @Column(name = "cell_phone")
    @NotNull(message = "The cell phone can´t is empty")
    @Size(max = 9, min = 9, message = "Cell phone | Size incorrect")
    protected String cellPhone;

    @Column(name = "document")
    @NotNull(message = "The document can´t is empty")
    @Size(max = 8, min = 8, message = "Document incorrect | Size incorrect")
    private String document;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "account_id", referencedColumnName = "id_account")
    private Account account;
}