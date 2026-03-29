package com.vsks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/loginPage")
    public String loginPage() {
        System.out.println("/loginPage invoked");
        return "login";
    }
}
