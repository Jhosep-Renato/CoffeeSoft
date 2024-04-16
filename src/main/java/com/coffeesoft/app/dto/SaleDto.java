package com.coffeesoft.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SaleDto {

    private String product;

    private String price;

    private String quantity;

    private String total;


}
