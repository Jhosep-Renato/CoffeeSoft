package com.coffeesoft.app.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private int idSale;

    @Column(name = "date_sale")
    private Date dateSale;

    @Column(name = "min_sale")
    private Long minSale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier_id", referencedColumnName = "id")
    private Cashier cashier;

    @Column(name = "total_sale")
    private double totalSale;
}
