package com.coffeesoft.app.repository.radmin;

import com.coffeesoft.app.entity.Cashier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    @Transactional
    public Cashier findCashier(int dni) throws NoResultException {

        TypedQuery<Cashier> typedQuery = entityManager
                                                .createQuery("FROM Cashier WHERE document =:dni", Cashier.class);
        typedQuery.setParameter("dni", dni);

        Cashier cashier = entityManager.find(Cashier.class, typedQuery.getSingleResult().getId());

        return cashier;
    }
}
