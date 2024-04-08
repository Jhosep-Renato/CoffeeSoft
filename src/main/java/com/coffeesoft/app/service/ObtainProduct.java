package com.coffeesoft.app.service;

import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObtainProduct implements IObtainService {

    private final IProductRepository repository;

    public ObtainProduct(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> products() {
        return repository.findAll();
    }
}
