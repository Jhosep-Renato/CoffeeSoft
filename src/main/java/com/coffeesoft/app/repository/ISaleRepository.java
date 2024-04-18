package com.coffeesoft.app.repository;

import com.coffeesoft.app.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ISaleRepository extends JpaRepository<Sale, Integer> {

}
