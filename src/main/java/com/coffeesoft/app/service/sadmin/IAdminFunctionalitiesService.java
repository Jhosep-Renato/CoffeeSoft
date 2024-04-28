package com.coffeesoft.app.service.sadmin;

import com.coffeesoft.app.entity.Cashier;

import java.util.Optional;

public interface IAdminFunctionalitiesService {

    void saveCashier(Cashier theCashier);

    Optional<Cashier> searchCashier(long dni);

    void updateCashier(Cashier cashier);
}
