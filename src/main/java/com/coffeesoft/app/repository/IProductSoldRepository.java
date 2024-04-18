package com.coffeesoft.app.repository;

import com.coffeesoft.app.entity.ProductsSold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductSoldRepository extends JpaRepository<ProductsSold, Integer> {
}
