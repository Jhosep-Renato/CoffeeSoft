package com.coffeesoft.app.service.scashier;

import com.coffeesoft.app.dto.SaleDto;

import java.util.Set;

public interface ISalesService {

    void saveSales(Set<SaleDto> sales);
}
