package com.coffeesoft.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products_sold") @Getter @Setter
public class ProductsSold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_products_sold")
    private int idProductsSold;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "sale_id", referencedColumnName = "id_sale")
    private Sale saleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id_product")
    private Product productId;

    @Column(name = "quantity_product")
    private int quantityProduct;
}
