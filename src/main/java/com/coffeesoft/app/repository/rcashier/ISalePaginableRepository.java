package com.coffeesoft.app.repository.rcashier;

import com.coffeesoft.app.entity.Sale;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ISalePaginableRepository extends PagingAndSortingRepository<Sale, Integer> {

}
