package com.coffeesoft.app.service.sadmin;

import com.coffeesoft.app.entity.Account;
import com.coffeesoft.app.entity.Cashier;
import com.coffeesoft.app.repository.radmin.IAdminFunctionalitiesRepository;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminFunctionalitiesImpl implements IAdminFunctionalitiesService {

    private final IAdminFunctionalitiesRepository adminRepository;

    public AdminFunctionalitiesImpl(IAdminFunctionalitiesRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void saveCashier(Cashier theCashier) {

        Account account = new Account();
        account.setUsername(theCashier.getDocument());
        account.setPassword("1234");
        account.setRole("EMPLOYEE");

        theCashier.setAccount(account);

        adminRepository.saveCashier(theCashier);

    }

    @Override
    public Optional<Cashier> searchCashier(long dni) {

        if (dni == 0) {
            throw new NullPointerException("The DNI can't be null");
        }

        Cashier cashier = null;

        try {

            cashier = adminRepository.searchCashier(dni);

        } catch (NoResultException ignored) {
        }

        return Optional.ofNullable(cashier);

    }

    @Override
    public void updateCashier(Cashier cashier) {

        long dni = Long.parseLong(cashier.getDocument());

        Optional<Cashier> cashierOptional = searchCashier(dni);

        if (cashierOptional.isPresent()) {

            Account account = cashierOptional.get().getAccount();

            account.setUsername(cashier.getDocument());
            cashier.setAccount(account);

            adminRepository.updateCashier(cashier);
        }
    }
}
