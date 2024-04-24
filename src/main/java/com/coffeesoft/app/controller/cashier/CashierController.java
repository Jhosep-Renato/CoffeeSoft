package com.coffeesoft.app.controller.cashier;

import com.coffeesoft.app.dto.SaleDto;
import com.coffeesoft.app.entity.Product;
import com.coffeesoft.app.entity.Sale;
import com.coffeesoft.app.service.scashier.ISalesProductsService;
import com.coffeesoft.app.service.scashier.ISalesService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CashierController {

    private final ISalesService obtainService;

    private final ISalesProductsService salesProductsService;

    @Lazy
    public CashierController(ISalesService obtainService, ISalesProductsService salesProductsService) {
        this.obtainService = obtainService;
        this.salesProductsService = salesProductsService;
    }

    @GetMapping("/home-cashier")
    public String homeCashier(Model model) {

        Set<Product> theProducts = salesProductsService.getProducts();

        model.addAttribute("theProducts", theProducts);

        return "html/cashier/home-cashier";
    }

    @GetMapping("/show-sale")
    public String showSale(@PageableDefault Pageable pageable,
                           Model model) {

        Page<Sale> pageSale = salesProductsService.findSaleAll(pageable);

        model.addAttribute("theSales", pageSale);

        int totalPages = pageSale.getTotalPages();
        int currentPage = pageSale.getNumber();

        int start = Math.max(1, currentPage);
        int end = Math.min(currentPage + 5, totalPages);


        if (totalPages > 0) {

            List<Integer> pageNumbers = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                pageNumbers.add(i);
            }

            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "html/cashier/show_sale";
    }

    @PostMapping("/processProduct")
    public String processProduct(@RequestBody Set<SaleDto> saleDto) {

        obtainService.saveSales(saleDto);

        return "html/cashier/home-cashier";
    }

}
