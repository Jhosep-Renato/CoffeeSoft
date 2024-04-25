package com.coffeesoft.app.service.scashier;

import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.*;
import com.coffeesoft.app.repository.rcashier.IProductRepository;
import com.coffeesoft.app.repository.rcashier.IProductSoldRepository;
import com.coffeesoft.app.repository.rcashier.ISaleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.*;

@Service
public class SalesImpl implements ISalesService {

    private final IProductRepository productRepository;

    private final ISaleRepository saleRepository;

    private final IProductSoldRepository productSoldRepository;

    @Lazy
    public SalesImpl(IProductRepository productRepository, IProductSoldRepository productSoldRepository,
                     ISaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.productSoldRepository = productSoldRepository;
        this.saleRepository = saleRepository;

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
}
