package com.coffeesoft.app.repository.rcashier;

import com.coffeesoft.app.model.entity.Cashier;
import com.coffeesoft.app.model.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;


public interface ISaleRepository extends JpaRepository<Sale, Integer> {
}
