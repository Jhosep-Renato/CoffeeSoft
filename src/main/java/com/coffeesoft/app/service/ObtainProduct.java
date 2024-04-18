package com.coffeesoft.app.service;

import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.Cashier;
import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.entity.ProductsSold;
import com.coffeesoft.app.entity.Sale;
import com.coffeesoft.app.repository.IProductRepository;
import com.coffeesoft.app.repository.IProductSoldRepository;
import com.coffeesoft.app.repository.ISaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<Product> products() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void saveSales(List<SaleDto> saleDtoList) {

        try {

            Sale sale = new Sale();
            sale.setDateSale(new Date());
            sale.setMinSale(new Time(System.currentTimeMillis()));

            sale.setTotalSale(saleDtoList.stream()
                    .mapToDouble(productSale -> {
                        double price = Double.parseDouble(productSale.getPrice());

                        return price * Integer.parseInt(productSale.getQuantity().trim());
                    })
                    .sum());

            Cashier cashier = new Cashier();
            cashier.setId(1);

            sale.setCashier(cashier);

            Sale findSale = saleRepository.save(sale);

            saveProduct(saleDtoList, findSale);

        } catch (NullPointerException exception) {
            throw new NullPointerException("There are null products");
        }
    }

    @Override
    public List<Sale> findSaleAll() {

            return saleRepository.findAll();
    }

    @Transactional
    private void saveProduct(List<SaleDto> listSales, Sale sale) throws NullPointerException {

        List<ProductsSold> productSales = new ArrayList<>();

        for (SaleDto saleDto : listSales) {

            Product product = repository.findProduct(saleDto.getProduct().trim());
            int quantity = Integer.parseInt(saleDto.getQuantity().trim());


            ProductsSold productSold = new ProductsSold();
            productSold.setQuantityProduct(quantity);
            productSold.setProductId(product);
            productSold.setSaleId(sale);

            productSales.add(productSold);
        }

        productSoldRepository.saveAll(productSales);
    }
}
