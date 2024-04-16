package com.coffeesoft.app.controller;

import com.coffeesoft.app.dto.ProductDto;
import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.service.IObtainService;
import com.coffeesoft.app.service.ObtainProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    private final IObtainService serviceProduct;

    public IndexController(IObtainService serviceProduct) {
        this.serviceProduct = serviceProduct;
    }

    @GetMapping("/home")
    public String index(Model model) {

        List<Product> theProducts = serviceProduct.products();

        model.addAttribute("theProducts", theProducts);
        model.addAttribute("theProductDto", new ProductDto());
        return "html/index";
    }

    @PostMapping("/processProduct")
    public String process(@RequestBody List<SaleDto> saleDto, Model model) {

        boolean verification = serviceProduct.saveSales(saleDto);

        System.out.println(verification);
        model.addAttribute("verification", verification);

        return "html/test";
    }

}
