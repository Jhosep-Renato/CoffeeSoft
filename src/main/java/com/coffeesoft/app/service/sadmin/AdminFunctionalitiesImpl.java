package com.coffeesoft.app.service.sadmin;

import com.coffeesoft.app.model.entity.Account;
import com.coffeesoft.app.model.entity.Cashier;
import com.coffeesoft.app.model.entity.Role;
import com.coffeesoft.app.repository.radmin.IAdminFunctionalitiesRepository;
import jakarta.persistence.NoResultException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.coffeesoft.app.model.enums.Role.*;

import java.util.Optional;

@Service
public class AdminFunctionalitiesImpl implements IAdminFunctionalitiesService {

    private final IAdminFunctionalitiesRepository adminRepository;

    public AdminFunctionalitiesImpl(IAdminFunctionalitiesRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional
    public void saveCashier(Cashier theCashier) {

        Account account = new Account();
        account.setUsername(theCashier.getDocument());
        account.setPassword(new BCryptPasswordEncoder().encode("123"));
        account.setRole(new Role(CASHIER.getId(), CASHIER.getRole()));

        theCashier.setAccount(account);

        adminRepository.saveCashier(theCashier);

    }

    @Override
    @Transactional
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
    @Transactional
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
