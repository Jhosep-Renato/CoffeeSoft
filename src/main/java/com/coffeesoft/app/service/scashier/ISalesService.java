package com.coffeesoft.app.service.scashier;

import com.coffeesoft.app.model.dto.SaleDto;

import java.util.Set;

public interface ISalesService {

    void saveSales(Set<SaleDto> sales, String nameCashier);
}
