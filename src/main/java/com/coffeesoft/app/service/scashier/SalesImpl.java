package com.coffeesoft.app.service.scashier;

import com.coffeesoft.app.model.dto.SaleDto;
import com.coffeesoft.app.model.entity.Cashier;
import com.coffeesoft.app.model.entity.Product;
import com.coffeesoft.app.model.entity.ProductsSold;
import com.coffeesoft.app.model.entity.Sale;
import com.coffeesoft.app.repository.rcashier.IProductRepository;
import com.coffeesoft.app.repository.rcashier.IProductSoldRepository;
import com.coffeesoft.app.repository.rcashier.ISaleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

@Service
public class SalesImpl implements ISalesService {

    private final IProductRepository productRepository;

    private final ISaleRepository saleRepository;

    private final IProductSoldRepository productSoldRepository;

    private final EntityManager entityManager;


    @Lazy
    public SalesImpl(IProductRepository productRepository, IProductSoldRepository productSoldRepository,
                     ISaleRepository saleRepository, EntityManager entityManager) {

        this.productRepository = productRepository;
        this.productSoldRepository = productSoldRepository;
        this.saleRepository = saleRepository;
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void saveSales(Set<SaleDto> saleProducts, String nameCashier) {

        try {

            LocalDate localDate = LocalDate.now();

            Sale sale = new Sale();
            sale.setDateSale(Date.valueOf(localDate));
            sale.setMinSale(new Time(System.currentTimeMillis()));

            sale.setTotalSale(saleProducts.stream()
                    .mapToDouble(productSale -> {
                        double price = Double.parseDouble(productSale.getPrice());

                        return price * Integer.parseInt(productSale.getQuantity().trim());
                    })
                    .sum());

            sale.setCashier(findCashier(nameCashier));

            Sale copySaleObject = saleRepository.save(sale);

            saveProduct(saleProducts, copySaleObject);

        } catch (NullPointerException exception) {
            throw new NullPointerException("There are null getProducts");
        }
    }


    @Transactional
    private void saveProduct(Set<SaleDto> saleProducts, Sale copySaleObject) throws NullPointerException {

        List<ProductsSold> productSales = new ArrayList<>();

        for (SaleDto saleDto : saleProducts) {

            Product product = productRepository.findProduct(saleDto.getProduct().trim());
            int quantity = Integer.parseInt(saleDto.getQuantity().trim());


            ProductsSold productSold = new ProductsSold();
            productSold.setQuantityProduct(quantity);
            productSold.setProductId(product);
            productSold.setSaleId(copySaleObject);

            productSales.add(productSold);
        }

        productSoldRepository.saveAll(productSales);
    }


    private  Cashier findCashier(String documentCashier) {

        final String HQL = "FROM Cashier WHERE document =:documentCashier";

        TypedQuery<Cashier> typedQuery = entityManager
                .createQuery(HQL, Cashier.class);

        typedQuery.setParameter("documentCashier", documentCashier);

        Cashier cashier = null;

        try {

            cashier = typedQuery.getSingleResult();

        } catch (RuntimeException ignored) {
        }

        return cashier;
    }
}
