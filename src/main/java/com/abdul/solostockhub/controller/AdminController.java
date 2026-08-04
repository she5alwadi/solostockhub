package com.abdul.solostockhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abdul.solostockhub.service.AppUserService;
import com.abdul.solostockhub.service.ProductService;

@Controller
public class AdminController {

    private final AppUserService appUserService;
    private final ProductService productService;

    public AdminController(
            AppUserService appUserService,
            ProductService productService) {

        this.appUserService = appUserService;
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {

        model.addAttribute(
                "users",
                appUserService.getAllUsers()
        );

        model.addAttribute(
                "userCount",
                appUserService.getUserCount()
        );

        model.addAttribute(
                "productCount",
                productService.getTotalProductCount()
        );

        model.addAttribute(
                "lowStockCount",
                productService.getLowStockCount()
        );

        return "admin/dashboard";
    }
}