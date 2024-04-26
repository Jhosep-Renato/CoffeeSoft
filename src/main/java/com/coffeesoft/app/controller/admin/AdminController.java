package com.coffeesoft.app.controller.admin;

import com.coffeesoft.app.entity.Cashier;
import com.coffeesoft.app.service.sadmin.ICashierFunctionalitiesService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AdminController {

    private final ICashierFunctionalitiesService cashierService;

    public AdminController(ICashierFunctionalitiesService cashierService) {
        this.cashierService = cashierService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/home-admin")
    public String home(Model model) {

        model.addAttribute("theCashier", new Cashier());

        return "html/admin/home-admin";
    }

    @GetMapping("/update-cashier")
    public String updateCashier(Model model) {

        model.addAttribute("theCashier", new Cashier());

        return "html/admin/update-cashier";
    }

    @GetMapping("/search-dni")
    public String searchDni(@RequestParam(defaultValue = "0") int dni, Model model) {

        Optional<Cashier> optionalCashier = cashierService.updateCashier(dni);

        if (optionalCashier.isPresent()) {

            model.addAttribute("theCashier", optionalCashier.get());
        } else {

            model.addAttribute("theCashier", new Cashier());
            model.addAttribute("searchMessage", "Cashier NOT FOUND");
        }

        return "html/admin/update-cashier";
    }


    @PostMapping("/process-request")
    public String processRequest(@Valid @ModelAttribute("theCashier") Cashier theCashier,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "html/admin/home-admin";
        }

        cashierService.saveCashier(theCashier);
        model.addAttribute("verification", true);
        return "html/admin/home-admin";
    }
}
