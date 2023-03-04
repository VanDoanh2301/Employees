package com.example.managenment.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accessDenied")
public class accessDenied {
    @GetMapping("")
    public String accessDenied() {
        return "admin/accessDenied";
    }
}
