package com.coffeesoft.app.service.sadmin;

import com.coffeesoft.app.entity.Account;
import com.coffeesoft.app.entity.Cashier;
import com.coffeesoft.app.repository.radmin.ICashierFunctionalitiesRepository;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CashierFunctionalitiesImpl implements ICashierFunctionalitiesService {

    private final ICashierFunctionalitiesRepository cashierService;

    public CashierFunctionalitiesImpl(ICashierFunctionalitiesRepository cashierService) {
        this.cashierService = cashierService;
    }

    @Override
    public void saveCashier(Cashier theCashier) {

        Account account = new Account();
        account.setUsername(theCashier.getDocument());
        account.setPassword("1234");
        account.setRole("EMPLOYEE");

        theCashier.setAccount(account);

        cashierService.saveCashier(theCashier);

    }

    @Override
    public Optional<Cashier> updateCashier(int dni) {

        if (dni == 0) {
            throw new NullPointerException("The DNI can't be null");
        }

        Cashier cashier = null;

        try {

            cashier = cashierService.findCashier(dni);

        } catch (NoResultException ignored) {
        }

        return Optional.ofNullable(cashier);

    }


}
