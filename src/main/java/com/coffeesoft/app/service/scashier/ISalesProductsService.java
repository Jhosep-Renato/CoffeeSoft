package com.coffeesoft.app.service.scashier;

import com.coffeesoft.app.model.entity.Product;
import com.coffeesoft.app.model.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ISalesProductsService {

    Page<Sale> findSaleAll(Pageable pageable, String cashierDocument);

    Set<Product> getProducts();
}
