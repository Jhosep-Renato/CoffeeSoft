package com.coffeesoft.app.repository;

import com.coffeesoft.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
