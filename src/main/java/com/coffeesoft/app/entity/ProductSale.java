package com.coffeesoft.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product_sale") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct_sale")
    private int idProductSale;

    @Column(name = "quantity")
    private int quantityProduct;

    @Column(name = "product_id")
    private int idProduct;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "sale_product", joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "productsale_id"))
    private List<Sale> sales;
}
