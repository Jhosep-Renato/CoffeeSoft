package com.coffeesoft.app.service;

import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.entity.Sale;

import java.util.List;
import java.util.Set;

public interface IObtainService {

    Set<Product> products();

    void saveSales(Set<SaleDto> sales);

    List<Sale> findSaleAll();
}
