package com.abdul.solostockhub.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abdul.solostockhub.model.Product;
import com.abdul.solostockhub.model.ProductCategory;
import com.abdul.solostockhub.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Product not found with ID: " + id));
    }

    public Page<Product> getProducts(
            ProductCategory category,
            Long brandId,
            int page,
            int size,
            String sortField,
            String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by(sortField).descending()
                : Sort.by(sortField).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        if (category != null && brandId != null) {
            return productRepository.findByCategoryAndBrandBrandId(
                    category,
                    brandId,
                    pageable
            );
        }

        if (category != null) {
            return productRepository.findByCategory(category, pageable);
        }

        if (brandId != null) {
            return productRepository.findByBrandBrandId(brandId, pageable);
        }

        return productRepository.findAll(pageable);
    }

    public Page<Product> searchProductsByName(
            String keyword,
            int page,
            int size,
            String sortField,
            String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by(sortField).descending()
                : Sort.by(sortField).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findByNameContainingIgnoreCase(
                keyword,
                pageable
        );
    }

    public long getTotalProductCount() {
        return productRepository.count();
    }

    public long getLowStockCount() {
        return productRepository.countByQuantityLessThanEqual(10);
    }
}