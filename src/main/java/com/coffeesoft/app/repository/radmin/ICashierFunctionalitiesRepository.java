package com.coffeesoft.app.repository.radmin;

import com.coffeesoft.app.entity.Cashier;

public interface ICashierFunctionalitiesRepository {

    void saveCashier(Cashier theCashier);

    Cashier findCashier(int dni);
}
