package com.abdul.solostockhub.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abdul.solostockhub.model.Product;
import com.abdul.solostockhub.model.ProductCategory;
import com.abdul.solostockhub.service.BrandService;
import com.abdul.solostockhub.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService;

    public ProductController(
            ProductService productService,
            BrandService brandService) {

        this.productService = productService;
        this.brandService = brandService;
    }

    @GetMapping("/products")
    public String listProducts(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection,
            Model model) {

        Page<Product> productPage;

        if (keyword != null && !keyword.isBlank()) {
            productPage = productService.searchProductsByName(
                    keyword,
                    page,
                    size,
                    sortField,
                    sortDirection
            );
        } else {
            productPage = productService.getProducts(
                    category,
                    brandId,
                    page,
                    size,
                    sortField,
                    sortDirection
            );
        }

        model.addAttribute("productPage", productPage);
        model.addAttribute("products", productPage.getContent());

        model.addAttribute(
                "brands",
                brandService.getAllBrands()
        );

        model.addAttribute(
                "categories",
                ProductCategory.values()
        );

        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedBrandId", brandId);
        model.addAttribute("keyword", keyword);

        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        return "products/list";
    }

    @GetMapping("/products/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());

        model.addAttribute(
                "brands",
                brandService.getAllBrands()
        );

        model.addAttribute(
                "categories",
                ProductCategory.values()
        );

        return "products/form";
    }

    @PostMapping("/products")
    public String createProduct(
            @Valid @ModelAttribute("product") Product product,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(
                    "brands",
                    brandService.getAllBrands()
            );

            model.addAttribute(
                    "categories",
                    ProductCategory.values()
            );

            return "products/form";
        }

        Product savedProduct = productService.saveProduct(product);

        return "redirect:/products/" + savedProduct.getProductId();
    }

    @GetMapping("/products/{id}")
    public String viewProduct(
            @PathVariable Long id,
            Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);

        return "products/details";
    }
}