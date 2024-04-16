package com.coffeesoft.app.service;

import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.Product;

import java.util.List;

public interface IObtainService {

    List<Product> products();

    boolean saveSales(List<SaleDto> saleDtos);
}
