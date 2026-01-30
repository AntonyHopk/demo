package com.example.demo.service;

import com.example.demo.dto.ProductCreateRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.mapper.DTOMapper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transient
    public ProductResponse createProduct(ProductCreateRequest product) {
        if (product.price() == null || product.price().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        Product newProduct = productRepository.save(new Product(product.price(), product.title()));
        return DTOMapper.toProductResponse(newProduct);
    }
}
