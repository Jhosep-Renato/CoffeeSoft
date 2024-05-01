package com.coffeesoft.app.repository.radmin;

import com.coffeesoft.app.model.entity.Cashier;

public interface IAdminFunctionalitiesRepository {

    void saveCashier(Cashier theCashier);

    Cashier searchCashier(long dni);

    void updateCashier(Cashier cashier);
}
