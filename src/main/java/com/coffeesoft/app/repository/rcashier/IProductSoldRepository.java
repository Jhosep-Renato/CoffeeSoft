package com.coffeesoft.app.repository.rcashier;

import com.coffeesoft.app.model.entity.ProductsSold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductSoldRepository extends JpaRepository<ProductsSold, Integer> {
}
