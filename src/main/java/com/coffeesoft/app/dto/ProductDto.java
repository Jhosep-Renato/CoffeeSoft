package com.coffeesoft.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDto {

    private int id;
    private String product;
    private double price;
    private int quantity;

}
