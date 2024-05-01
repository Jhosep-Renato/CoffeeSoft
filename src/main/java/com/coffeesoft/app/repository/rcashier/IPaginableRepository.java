package com.coffeesoft.app.repository.rcashier;

import com.coffeesoft.app.model.entity.Cashier;
import com.coffeesoft.app.model.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;

public interface IPaginableRepository extends PagingAndSortingRepository<Sale, Integer> {

    Page<Sale> findByCashierAndDateSale(Cashier cashier, Date date, Pageable pageable);
}
