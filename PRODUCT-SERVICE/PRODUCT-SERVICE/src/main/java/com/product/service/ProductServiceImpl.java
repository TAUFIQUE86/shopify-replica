package com.product.service;

import com.product.dto.ProductDto;
import com.product.entity.Product;
import com.product.mapper.ProductMapper;
import com.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements  ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

/*
    @Override
    public List<ProductDto> searchProduct(String keyword) {


        List<Product> products = productRepository.searchProducts(keyword);

        ArrayList<ProductDto> productDtos = new ArrayList<>();

       for (Product p:products){

             productDtos.add(ProductMapper.convertProductToDto(p));

       }
        return productDtos;
    }
                /*
                        // ✅ Custom mapper handles everything
                        return products.stream()
                                .map(ProductMapper::convertProductToDto)
                                .collect(Collectors.toList());
                    }


*/


   @Override
public List<ProductDto> searchProduct(String keyword) {

    String key = keyword.toLowerCase();

    List<Product> products = productRepository.searchProducts(keyword);

    List<ProductDto> productDtos = new ArrayList<>();

    for (Product p : products) {

        ProductDto dto = ProductMapper.convertProductToDto(p);

        // 🔍 check if keyword matches brand
        boolean brandMatch = p.getBrands().stream()
                .anyMatch(b -> b.getName() != null &&
                               b.getName().toLowerCase().contains(key));

        // 🔥 filter ONLY when brand matches
        if (brandMatch) {
            dto.setBrands(
                dto.getBrands().stream()
                    .filter(b -> b.getName() != null &&
                                 b.getName().toLowerCase().contains(key))
                    .collect(Collectors.toSet())
            );
        }

        productDtos.add(dto);
    }

    return productDtos;
}


}



