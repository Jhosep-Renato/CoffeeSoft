package com.coffeesoft.app.service;

import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.*;
import com.coffeesoft.app.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.*;

@Service
public class ObtainProduct implements IObtainService {

    private final IProductRepository repository;
    private final ISaleRepository saleRepository;
    private final IProductSoldRepository productSoldRepository;

    public ObtainProduct(IProductRepository repository, ISaleRepository saleRepository,
                         IProductSoldRepository productSoldRepository) {
        this.repository = repository;
        this.saleRepository = saleRepository;
        this.productSoldRepository = productSoldRepository;
    }


    @Override
    public Set<Product> products() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    @Transactional
    public void saveSales(Set<SaleDto> saleProducts) {

        try {

            Sale sale = new Sale();
            sale.setDateSale(new Date());
            sale.setMinSale(new Time(System.currentTimeMillis()));

            sale.setTotalSale(saleProducts.stream()
                    .mapToDouble(productSale -> {
                        double price = Double.parseDouble(productSale.getPrice());

                        return price * Integer.parseInt(productSale.getQuantity().trim());
                    })
                    .sum());

            Cashier cashier = new Cashier();
            cashier.setId(1);

            sale.setCashier(cashier);

            Sale copySaleObject = saleRepository.save(sale);

            saveProduct(saleProducts, copySaleObject);

        } catch (NullPointerException exception) {
            throw new NullPointerException("There are null products");
        }
    }

    @Override
    public List<Sale> findSaleAll() {

            return saleRepository.findAll();
    }

    @Transactional
    private void saveProduct(Set<SaleDto> saleProducts, Sale copySaleObject) throws NullPointerException {

        List<ProductsSold> productSales = new ArrayList<>();

        for (SaleDto saleDto : saleProducts) {

            Product product = repository.findProduct(saleDto.getProduct().trim());
            int quantity = Integer.parseInt(saleDto.getQuantity().trim());


            ProductsSold productSold = new ProductsSold();
            productSold.setQuantityProduct(quantity);
            productSold.setProductId(product);
            productSold.setSaleId(copySaleObject);

            productSales.add(productSold);
        }

        productSoldRepository.saveAll(productSales);
    }
}
