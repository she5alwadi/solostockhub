package com.abdul.solostockhub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abdul.solostockhub.model.Brand;
import com.abdul.solostockhub.repository.BrandRepository;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAllByOrderByBrandNameAsc();
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Brand not found with ID: " + id));
    }
}