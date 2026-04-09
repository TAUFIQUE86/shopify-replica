package com.product.service;

import com.product.entity.Product;
import com.product.exception.ResourceNotFoundException;
import com.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class GlobalExceptionHandlerImpl implements GlobalExceptionHandler{
    private  final  ProductRepository productRepository;

    public GlobalExceptionHandlerImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () ->  new ResourceNotFoundException("Product not found with id: " + id)
                );
    }
}
