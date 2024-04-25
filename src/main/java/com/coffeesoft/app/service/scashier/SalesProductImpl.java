package com.coffeesoft.app.service.scashier;

import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.entity.Sale;
import com.coffeesoft.app.repository.rcashier.IProductRepository;
import com.coffeesoft.app.repository.rcashier.ISalePaginableRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SalesProductImpl implements ISalesProductsService {

    private final ISalePaginableRepository paginableRepository;

    private final IProductRepository productRepository;

    @Lazy
    public SalesProductImpl(ISalePaginableRepository paginableRepository, IProductRepository productRepository) {
        this.paginableRepository = paginableRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Page<Sale> findSaleAll(Pageable pageable) {

        return paginableRepository.findAll(pageable);
    }

    @Override
    public Set<Product> getProducts() {
        return new HashSet<>(productRepository.findAll());
    }

}
