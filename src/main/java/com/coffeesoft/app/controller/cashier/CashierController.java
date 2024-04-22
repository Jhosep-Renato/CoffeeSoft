package com.coffeesoft.app.controller.cashier;

import com.coffeesoft.app.dto.ProductDto;
import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.entity.Sale;
import com.coffeesoft.app.service.IObtainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class CashierController {

    private final IObtainService obtainService;

    public CashierController(IObtainService obtainService) {
        this.obtainService = obtainService;
    }

    @GetMapping("/home-cashier")
    public String homeCashier(Model model) {

        Set<Product> theProducts = obtainService.products();

        model.addAttribute("theProducts", theProducts);

        return "html/cashier/home-cashier";
    }

    @GetMapping("/show-sale")
    public String showSale(Model model) {

        List<Sale> theSale = obtainService.findSaleAll();

        model.addAttribute("theSales", theSale);

        return "html/cashier/show_sale";
    }

    @PostMapping("/processProduct")
    public String processProduct(@RequestBody Set<SaleDto> saleDto) {

        obtainService.saveSales(saleDto);

        return "html/cashier/home-cashier";
    }

}
