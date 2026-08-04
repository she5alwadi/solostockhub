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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestParam(required = false)
            ProductCategory category,

            @RequestParam(required = false)
            Long brandId,

            @RequestParam(required = false)
            String keyword,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "name")
            String sortField,

            @RequestParam(defaultValue = "asc")
            String sortDirection,

            Model model) {

        Page<Product> productPage;

        if (keyword != null && !keyword.isBlank()) {

            productPage =
                    productService.searchProductsByName(
                            keyword,
                            page,
                            size,
                            sortField,
                            sortDirection
                    );

        } else {

            productPage =
                    productService.getProducts(
                            category,
                            brandId,
                            page,
                            size,
                            sortField,
                            sortDirection
                    );
        }

        model.addAttribute(
                "productPage",
                productPage
        );

        model.addAttribute(
                "products",
                productPage.getContent()
        );

        model.addAttribute(
                "brands",
                brandService.getAllBrands()
        );

        model.addAttribute(
                "categories",
                ProductCategory.values()
        );

        model.addAttribute(
                "selectedCategory",
                category
        );

        model.addAttribute(
                "selectedBrandId",
                brandId
        );

        model.addAttribute(
                "keyword",
                keyword
        );

        model.addAttribute(
                "currentPage",
                page
        );

        model.addAttribute(
                "pageSize",
                size
        );

        model.addAttribute(
                "sortField",
                sortField
        );

        model.addAttribute(
                "sortDirection",
                sortDirection
        );

        return "products/list";
    }

    @GetMapping("/products/new")
    public String showCreateProductForm(
            Model model) {

        model.addAttribute(
                "product",
                new Product()
        );

        addFormOptions(model);

        model.addAttribute(
                "formTitle",
                "Add New Product"
        );

        model.addAttribute(
                "formAction",
                "/products"
        );

        return "products/form";
    }

    @PostMapping("/products")
    public String createProduct(
            @Valid
            @ModelAttribute("product")
            Product product,

            BindingResult bindingResult,

            @RequestParam(required = false)
            Long brandId,

            Model model,

            RedirectAttributes redirectAttributes) {

        attachBrand(
                product,
                brandId,
                bindingResult
        );

        if (bindingResult.hasErrors()) {

            addFormOptions(model);

            model.addAttribute(
                    "formTitle",
                    "Add New Product"
            );

            model.addAttribute(
                    "formAction",
                    "/products"
            );

            return "products/form";
        }

        Product savedProduct =
                productService.saveProduct(product);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Product created successfully."
        );

        return "redirect:/products/"
                + savedProduct.getProductId();
    }

    @GetMapping("/products/{id}")
    public String viewProduct(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "product",
                productService.getProductById(id)
        );

        return "products/details";
    }

    @GetMapping("/products/{id}/edit")
    public String showEditProductForm(
            @PathVariable Long id,
            Model model) {

        Product product =
                productService.getProductById(id);

        model.addAttribute(
                "product",
                product
        );

        model.addAttribute(
                "selectedBrandId",
                product.getBrand().getBrandId()
        );

        addFormOptions(model);

        model.addAttribute(
                "formTitle",
                "Edit Product"
        );

        model.addAttribute(
                "formAction",
                "/products/" + id + "/edit"
        );

        return "products/form";
    }

    @PostMapping("/products/{id}/edit")
    public String updateProduct(
            @PathVariable Long id,

            @Valid
            @ModelAttribute("product")
            Product formProduct,

            BindingResult bindingResult,

            @RequestParam(required = false)
            Long brandId,

            Model model,

            RedirectAttributes redirectAttributes) {

        Product existingProduct =
                productService.getProductById(id);

        attachBrand(
                formProduct,
                brandId,
                bindingResult
        );

        if (bindingResult.hasErrors()) {

            formProduct.setProductId(id);

            addFormOptions(model);

            model.addAttribute(
                    "selectedBrandId",
                    brandId
            );

            model.addAttribute(
                    "formTitle",
                    "Edit Product"
            );

            model.addAttribute(
                    "formAction",
                    "/products/" + id + "/edit"
            );

            return "products/form";
        }

        existingProduct.setName(
                formProduct.getName()
        );

        existingProduct.setDescription(
                formProduct.getDescription()
        );

        existingProduct.setPrice(
                formProduct.getPrice()
        );

        existingProduct.setSize(
                formProduct.getSize()
        );

        existingProduct.setColor(
                formProduct.getColor()
        );

        existingProduct.setQuantity(
                formProduct.getQuantity()
        );

        existingProduct.setCategory(
                formProduct.getCategory()
        );

        existingProduct.setBrand(
                formProduct.getBrand()
        );

        productService.saveProduct(existingProduct);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Product updated successfully."
        );

        return "redirect:/products/" + id;
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        productService.deleteProduct(id);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Product deleted successfully."
        );

        return "redirect:/products";
    }

    private void attachBrand(
            Product product,
            Long brandId,
            BindingResult bindingResult) {

        if (brandId == null) {

            bindingResult.rejectValue(
                    "brand",
                    "brand.required",
                    "Brand is required"
            );

            return;
        }

        try {

            product.setBrand(
                    brandService.getBrandById(brandId)
            );

        } catch (IllegalArgumentException exception) {

            bindingResult.rejectValue(
                    "brand",
                    "brand.invalid",
                    "Select a valid brand"
            );
        }
    }

    private void addFormOptions(Model model) {

        model.addAttribute(
                "brands",
                brandService.getAllBrands()
        );

        model.addAttribute(
                "categories",
                ProductCategory.values()
        );
    }
}