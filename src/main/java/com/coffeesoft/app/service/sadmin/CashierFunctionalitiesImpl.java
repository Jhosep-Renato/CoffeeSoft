package com.coffeesoft.app.service.sadmin;

import com.coffeesoft.app.entity.Account;
import com.coffeesoft.app.entity.Cashier;
import com.coffeesoft.app.repository.radmin.ICashierFunctionalitiesRepository;
import org.springframework.stereotype.Service;

@Service
public class CashierFunctionalitiesImpl implements ICashierFunctionalitiesService {

    private final ICashierFunctionalitiesRepository repository;

    public CashierFunctionalitiesImpl(ICashierFunctionalitiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCashier(Cashier theCashier) {

        Account account = new Account();
        account.setUsername(theCashier.getDocument());
        account.setPassword("1234");
        account.setRole("EMPLOYEE");

        theCashier.setAccount(account);

        repository.saveCashier(theCashier);

    }
}
