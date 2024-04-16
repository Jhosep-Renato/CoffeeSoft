package com.coffeesoft.app.repository;

import com.coffeesoft.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends JpaRepository<Product, Integer> {


    @Query("FROM Product WHERE nameProduct = :name")
    Product findProduct(@Param("name") String name);
}
