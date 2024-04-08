package com.coffeesoft.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_sale")
public class ProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct_sale")
    private int idProductSale;

    @Column(name = "quantity_product")
    private int quantityProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id_product")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", referencedColumnName = "id_sale")
    private Sale saleId;
}
