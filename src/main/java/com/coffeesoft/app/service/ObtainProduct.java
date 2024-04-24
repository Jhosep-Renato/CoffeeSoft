package com.coffeesoft.app.service;

import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.*;
import com.coffeesoft.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.*;

@Service
public class ObtainProduct implements IObtainService {

    private final IProductRepository productRepository;

    private final ISaleRepository saleRepository;

    private final IProductSoldRepository productSoldRepository;

    private final ISalePaginableRepository paginableRepository;

    @Lazy
    @Autowired
    public ObtainProduct(IProductRepository productRepository, IProductSoldRepository productSoldRepository,
                         ISaleRepository saleRepository, ISalePaginableRepository paginableRepository) {
        this.productRepository = productRepository;
        this.productSoldRepository = productSoldRepository;
        this.saleRepository = saleRepository;
        this.paginableRepository = paginableRepository;

    }

    @Override
    public Set<Product> products() {
        return new HashSet<>(productRepository.findAll());
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
    public Page<Sale> findSaleAll(Pageable pageable) {

            return paginableRepository.findAll(pageable);
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

    private List<Sale> obtainSale() throws NullPointerException{

        return saleRepository.findAll();
    }
}
