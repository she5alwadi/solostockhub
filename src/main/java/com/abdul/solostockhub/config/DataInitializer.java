package com.abdul.solostockhub.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.abdul.solostockhub.model.AppUser;
import com.abdul.solostockhub.model.Brand;
import com.abdul.solostockhub.model.Product;
import com.abdul.solostockhub.model.ProductCategory;
import com.abdul.solostockhub.model.Role;
import com.abdul.solostockhub.repository.AppUserRepository;
import com.abdul.solostockhub.repository.BrandRepository;
import com.abdul.solostockhub.repository.ProductRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeApplicationData(
            AppUserRepository appUserRepository,
            BrandRepository brandRepository,
            ProductRepository productRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            createOrUpdateUser(
                    appUserRepository,
                    passwordEncoder,
                    "SoloStock",
                    "Administrator",
                    "admin",
                    "admin@solostockhub.com",
                    "Admin123!",
                    Role.ADMIN
            );

            createOrUpdateUser(
                    appUserRepository,
                    passwordEncoder,
                    "Warehouse",
                    "Staff",
                    "staff",
                    "staff@solostockhub.com",
                    "Staff123!",
                    Role.STAFF
            );

            createOrUpdateUser(
                    appUserRepository,
                    passwordEncoder,
                    "Sample",
                    "Customer",
                    "customer",
                    "customer@solostockhub.com",
                    "Customer123!",
                    Role.CUSTOMER
            );

            Brand nike = createBrandIfMissing(
                    brandRepository,
                    "Nike",
                    "United States",
                    "support@nike.com"
            );

            Brand adidas = createBrandIfMissing(
                    brandRepository,
                    "Adidas",
                    "Germany",
                    "support@adidas.com"
            );

            Brand puma = createBrandIfMissing(
                    brandRepository,
                    "Puma",
                    "Germany",
                    "support@puma.com"
            );

            Brand samsung = createBrandIfMissing(
                    brandRepository,
                    "Samsung",
                    "South Korea",
                    "support@samsung.com"
            );

            Brand apple = createBrandIfMissing(
                    brandRepository,
                    "Apple",
                    "United States",
                    "support@apple.com"
            );

            createProductIfMissing(
                    productRepository,
                    "Nike Air Max Shoes",
                    "Comfortable running shoes designed for everyday use.",
                    "129.99",
                    "10",
                    "Black",
                    25,
                    ProductCategory.FOOTWEAR,
                    nike
            );

            createProductIfMissing(
                    productRepository,
                    "Adidas Training Shirt",
                    "Lightweight athletic shirt suitable for gym training.",
                    "39.99",
                    "Large",
                    "Blue",
                    40,
                    ProductCategory.CLOTHING,
                    adidas
            );

            createProductIfMissing(
                    productRepository,
                    "Puma Sports Cap",
                    "Adjustable sports cap with breathable fabric.",
                    "24.99",
                    "One Size",
                    "Red",
                    8,
                    ProductCategory.ACCESSORIES,
                    puma
            );

            createProductIfMissing(
                    productRepository,
                    "Samsung Galaxy Tablet",
                    "Portable tablet designed for work and entertainment.",
                    "499.99",
                    "11 Inch",
                    "Silver",
                    12,
                    ProductCategory.ELECTRONICS,
                    samsung
            );

            createProductIfMissing(
                    productRepository,
                    "Apple Wireless Headphones",
                    "Wireless headphones with high-quality audio and charging case.",
                    "249.99",
                    "Standard",
                    "White",
                    6,
                    ProductCategory.ELECTRONICS,
                    apple
            );

            createProductIfMissing(
                    productRepository,
                    "Nike Running Shorts",
                    "Breathable running shorts with secure side pockets.",
                    "44.99",
                    "Medium",
                    "Grey",
                    30,
                    ProductCategory.CLOTHING,
                    nike
            );

            createProductIfMissing(
                    productRepository,
                    "Adidas Backpack",
                    "Durable backpack suitable for school, work, or travel.",
                    "59.99",
                    "25 Litres",
                    "Black",
                    18,
                    ProductCategory.ACCESSORIES,
                    adidas
            );

            createProductIfMissing(
                    productRepository,
                    "Puma Soccer Ball",
                    "Training soccer ball suitable for indoor and outdoor use.",
                    "34.99",
                    "Size 5",
                    "White",
                    9,
                    ProductCategory.SPORTS,
                    puma
            );

            createProductIfMissing(
                    productRepository,
                    "Samsung Smart Monitor",
                    "High-resolution monitor with built-in smart applications.",
                    "399.99",
                    "32 Inch",
                    "Black",
                    15,
                    ProductCategory.ELECTRONICS,
                    samsung
            );

            createProductIfMissing(
                    productRepository,
                    "Apple Charging Cable",
                    "Durable charging cable compatible with supported Apple devices.",
                    "29.99",
                    "1 Metre",
                    "White",
                    50,
                    ProductCategory.ELECTRONICS,
                    apple
            );

            System.out.println(
                    "SoloStock Hub startup data initialized successfully."
            );
        };
    }

    private void createOrUpdateUser(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            String firstName,
            String lastName,
            String username,
            String email,
            String rawPassword,
            Role role) {

        AppUser user = appUserRepository
                .findByUsernameIgnoreCase(username)
                .orElseGet(AppUser::new);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email.toLowerCase());
        user.setPassword(
                passwordEncoder.encode(rawPassword)
        );
        user.setRole(role);
        user.setEnabled(true);

        appUserRepository.save(user);
    }

    private Brand createBrandIfMissing(
            BrandRepository brandRepository,
            String brandName,
            String country,
            String contactEmail) {

        return brandRepository
                .findByBrandNameIgnoreCase(brandName)
                .orElseGet(() -> {

                    Brand brand = new Brand();

                    brand.setBrandName(brandName);
                    brand.setCountry(country);
                    brand.setContactEmail(contactEmail);

                    return brandRepository.save(brand);
                });
    }

    private void createProductIfMissing(
            ProductRepository productRepository,
            String name,
            String description,
            String price,
            String size,
            String color,
            Integer quantity,
            ProductCategory category,
            Brand brand) {

        if (productRepository.existsByNameIgnoreCase(name)) {
            return;
        }

        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(new BigDecimal(price));
        product.setSize(size);
        product.setColor(color);
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setBrand(brand);

        productRepository.save(product);
    }
}