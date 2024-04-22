package com.coffeesoft.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/home-admin")
    public String home() {

        return "html/admin/home-admin";
    }
}
