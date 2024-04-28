package com.coffeesoft.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coffee-soft")
public class LoginController {

    @GetMapping("/form")
    public String login() {

        return "html/form";
    }
}
