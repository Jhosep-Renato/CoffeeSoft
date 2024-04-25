package com.coffeesoft.app.controller.admin;

import com.coffeesoft.app.entity.Cashier;
import com.coffeesoft.app.service.sadmin.ICashierFunctionalitiesService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final ICashierFunctionalitiesService service;

    public AdminController(ICashierFunctionalitiesService service) {
        this.service = service;
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

    @PostMapping("/process-request")
    public String processRequest(@Valid @ModelAttribute("theCashier") Cashier theCashier,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "html/admin/home-admin";
        }

        service.saveCashier(theCashier);
        model.addAttribute("verification", true);
        return "html/admin/home-admin";
    }
}
