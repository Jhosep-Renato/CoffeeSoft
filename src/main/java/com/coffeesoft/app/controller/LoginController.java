package com.coffeesoft.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/form")
    public String login() {

        return "html/form";
    }

    @GetMapping("/forbidden")
    public String forbidden() {

        return "html/forbidden";
    }
}
