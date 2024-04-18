package com.coffeesoft.app.service;

import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.entity.Sale;

import java.util.List;

public interface IObtainService {

    List<Product> products();

    void saveSales(List<SaleDto> saleDtos);

    List<Sale> findSaleAll();
}
