package com.coffeesoft.app.controller;

import com.coffeesoft.app.dto.ProductDto;
import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.service.ObtainProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class IndexController {

    private final ObtainProduct serviceProduct;

    public IndexController(ObtainProduct serviceProduct) {
        this.serviceProduct = serviceProduct;
    }

    @GetMapping("/home")
    public String index(Model model) {

        List<Product> theProducts = serviceProduct.products();
        theProducts.forEach(System.out::println);

        model.addAttribute("theProducts", theProducts);
        model.addAttribute("theProductDto", new ProductDto());
        return "html/index";
    }

}
