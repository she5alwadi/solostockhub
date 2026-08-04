package com.abdul.solostockhub.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abdul.solostockhub.model.Product;
import com.abdul.solostockhub.model.ProductCategory;
import com.abdul.solostockhub.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(
            ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found with ID: " + id
                        )
                );
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    public Page<Product> getProducts(
            ProductCategory category,
            Long brandId,
            int page,
            int size,
            String sortField,
            String sortDirection) {

        String safeSortField = validateSortField(sortField);

        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by(safeSortField).descending()
                : Sort.by(safeSortField).ascending();

        Pageable pageable = PageRequest.of(
                Math.max(page, 0),
                size > 0 ? size : 5,
                sort
        );

        if (category != null && brandId != null) {
            return productRepository
                    .findByCategoryAndBrandBrandId(
                            category,
                            brandId,
                            pageable
                    );
        }

        if (category != null) {
            return productRepository
                    .findByCategory(category, pageable);
        }

        if (brandId != null) {
            return productRepository
                    .findByBrandBrandId(brandId, pageable);
        }

        return productRepository.findAll(pageable);
    }

    public Page<Product> searchProductsByName(
            String keyword,
            int page,
            int size,
            String sortField,
            String sortDirection) {

        String safeSortField = validateSortField(sortField);

        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by(safeSortField).descending()
                : Sort.by(safeSortField).ascending();

        Pageable pageable = PageRequest.of(
                Math.max(page, 0),
                size > 0 ? size : 5,
                sort
        );

        return productRepository
                .findByNameContainingIgnoreCase(
                        keyword.trim(),
                        pageable
                );
    }

    public long getTotalProductCount() {
        return productRepository.count();
    }

    public long getLowStockCount() {
        return productRepository
                .countByQuantityLessThanEqual(10);
    }

    private String validateSortField(String sortField) {

        if ("price".equals(sortField)
                || "quantity".equals(sortField)
                || "createdAt".equals(sortField)) {

            return sortField;
        }

        return "name";
    }
}