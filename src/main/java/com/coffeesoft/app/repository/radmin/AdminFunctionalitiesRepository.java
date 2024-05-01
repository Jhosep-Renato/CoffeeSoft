package com.coffeesoft.app.repository.radmin;

import com.coffeesoft.app.model.entity.Cashier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AdminFunctionalitiesRepository implements IAdminFunctionalitiesRepository {

    private final EntityManager entityManager;

    public AdminFunctionalitiesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveCashier(Cashier theCashier) {

        entityManager.persist(theCashier);
    }

    @Override
    @Transactional
    public Cashier searchCashier(long dni) throws NoResultException {

        TypedQuery<Cashier> typedQuery = entityManager
                                                .createQuery("FROM Cashier WHERE document =:dni", Cashier.class);
        typedQuery.setParameter("dni", dni);

        Cashier cashier = entityManager.find(Cashier.class, typedQuery.getSingleResult().getId());

        return cashier;
    }

    @Override
    @Transactional
    public void updateCashier(Cashier cashier) {

        entityManager.merge(cashier);
    }
}
