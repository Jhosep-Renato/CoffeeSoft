package com.coffeesoft.app.controller;

import com.coffeesoft.app.dto.ProductDto;
import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.entity.Sale;
import com.coffeesoft.app.service.IObtainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    private final IObtainService obtainService;

    public IndexController(IObtainService obtainService) {
        this.obtainService = obtainService;
    }

    @GetMapping("/home")
    public String index(Model model) {

        List<Product> theProducts = obtainService.products();

        model.addAttribute("theProducts", theProducts);
        model.addAttribute("theProductDto", new ProductDto());

        return "html/index";
    }

    @GetMapping("/show-sale")
    public String showSale(Model model) {

        List<Sale> theSale = obtainService.findSaleAll();

        model.addAttribute("theSales", theSale);

        return "html/show_sale";
    }

    @PostMapping("/processProduct")
    public String process(@RequestBody List<SaleDto> saleDto) {

        obtainService.saveSales(saleDto);

        return "html/test";
    }

}
