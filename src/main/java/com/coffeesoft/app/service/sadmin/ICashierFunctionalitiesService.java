package com.coffeesoft.app.service.sadmin;

import com.coffeesoft.app.entity.Cashier;

import java.util.Optional;

public interface ICashierFunctionalitiesService {

    void saveCashier(Cashier theCashier);

    Optional<Cashier> updateCashier(int dni);
}
