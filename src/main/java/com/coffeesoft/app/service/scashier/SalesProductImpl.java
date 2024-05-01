package com.coffeesoft.app.service.scashier;

import com.coffeesoft.app.model.entity.Cashier;
import com.coffeesoft.app.model.entity.Product;
import com.coffeesoft.app.model.entity.Sale;
import com.coffeesoft.app.repository.rcashier.IPaginableRepository;
import com.coffeesoft.app.repository.rcashier.IProductRepository;
import com.coffeesoft.app.repository.rcashier.ISaleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class SalesProductImpl implements ISalesProductsService {


    private final IProductRepository productRepository;

    private final ISaleRepository saleRepository;

    private final EntityManager entityManager;

    private final IPaginableRepository paginableRepository;

    @Lazy
    public SalesProductImpl(IProductRepository productRepository, ISaleRepository saleRepository,
                            EntityManager entityManager, IPaginableRepository paginableRepository) {

        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.entityManager = entityManager;
        this.paginableRepository = paginableRepository;
    }

    @Override
    public Page<Sale> findSaleAll(Pageable pageable, String cashierDocument) {

        LocalDate localDate = LocalDate.now();

        return paginableRepository.
                findByCashierAndDateSale(findCashierByDocument(cashierDocument), Date.valueOf(localDate), pageable);
    }

    @Override
    public Set<Product> getProducts() {
        return new HashSet<>(productRepository.findAll());
    }

    private Cashier findCashierByDocument(String document) {

        final String HQL = "FROM Cashier WHERE document =:document";

        TypedQuery<Cashier> typedQuery = entityManager.createQuery(HQL, Cashier.class);
        typedQuery.setParameter("document", document);

        Cashier cashier = null;

        try {

            cashier = typedQuery.getSingleResult();
        } catch(RuntimeException ignored) {
        }

        return cashier;
    }
}
