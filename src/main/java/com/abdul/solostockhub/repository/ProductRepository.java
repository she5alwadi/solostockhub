package com.abdul.solostockhub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdul.solostockhub.model.Product;
import com.abdul.solostockhub.model.ProductCategory;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    Page<Product> findByCategory(
            ProductCategory category,
            Pageable pageable
    );

    Page<Product> findByBrandBrandId(
            Long brandId,
            Pageable pageable
    );

    Page<Product> findByCategoryAndBrandBrandId(
            ProductCategory category,
            Long brandId,
            Pageable pageable
    );

    Page<Product> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    boolean existsByNameIgnoreCase(String name);

    long countByQuantityLessThanEqual(Integer quantity);
}