package com.coffeesoft.app.repository.radmin;

import com.coffeesoft.app.entity.Cashier;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CashierFunctionalitiesRepository implements ICashierFunctionalitiesRepository {

    private final EntityManager entityManager;

    public CashierFunctionalitiesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveCashier(Cashier theCashier) {

        entityManager.persist(theCashier);
    }
}
