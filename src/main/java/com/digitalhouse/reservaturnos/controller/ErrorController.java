package com.digitalhouse.reservaturnos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/403")
    public String accesoDenegado() {
        return "403";
    }
}
