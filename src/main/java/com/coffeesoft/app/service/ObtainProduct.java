package com.coffeesoft.app.service;

import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.Cashier;
import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.entity.ProductSale;
import com.coffeesoft.app.entity.Sale;
import com.coffeesoft.app.repository.IProductRepository;
import com.coffeesoft.app.repository.ISaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ObtainProduct implements IObtainService {

    private final IProductRepository repository;
    private final ISaleRepository saleRepository;

    public ObtainProduct(IProductRepository repository, ISaleRepository saleRepository) {
        this.repository = repository;
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Product> products() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public boolean saveSales(List<SaleDto> saleDtoList) {

        try {

            List<ProductSale> productSales = findProduct(saleDtoList);

            Sale sale = new Sale();
            sale.setDateSale(new Date());
            sale.setMinSale(1L);

            sale.setTotalSale(saleDtoList.stream()
                    .mapToDouble(productSale -> {
                        double price = Double.parseDouble(productSale.getPrice());

                        return price * Integer.parseInt(productSale.getQuantity().trim());
                    })
                    .sum());

            sale.setProductSale(productSales);
            Cashier cashier = new Cashier();
            cashier.setId(1);

            sale.setCashier(cashier);

            saleRepository.saveSale(sale);

            return true;

        } catch (NullPointerException exception) {
            return false;
        }
    }

    @Transactional
    private List<ProductSale> findProduct(List<SaleDto> listSales) throws NullPointerException {

        List<ProductSale> productSales = new ArrayList<>();

        for (SaleDto saleDto : listSales) {

            int idProduct = repository.findProduct(saleDto.getProduct().trim()).getIdProduct();
            int quantity = Integer.parseInt(saleDto.getQuantity().trim());


            ProductSale productSale = new ProductSale();
            productSale.setQuantityProduct(quantity);
            productSale.setIdProduct(idProduct);

            productSales.add(productSale);
        }

        return productSales;
    }
}
