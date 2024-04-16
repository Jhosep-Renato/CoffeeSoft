package com.coffeesoft.app.repository;

import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.entity.Sale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class SaleImplRepository implements ISaleRepository {

    private final EntityManager entityManager;

    public SaleImplRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveSale(Sale sale) {

        entityManager.persist(sale);
    }

    private Product findProduct(String productName) {

        TypedQuery<Product> query = entityManager
                                                .createQuery("FROM Product WHERE nameProduct =: productName", Product.class);


        return query.getSingleResult();
    }
}
