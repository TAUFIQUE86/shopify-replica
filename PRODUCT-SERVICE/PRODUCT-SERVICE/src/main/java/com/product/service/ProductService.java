package com.product.service;

import com.product.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

public  List<ProductDto> searchProduct(String keyword);

}
