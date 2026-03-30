package com.product.mapper;

import com.product.dto.*;
import com.product.entity.Product;
import org.modelmapper.ModelMapper;


public class ProductMapper {

    // model mapper

    private static final ModelMapper mapper = new ModelMapper();

    public static ProductDto convertProductToDto(Product product) {

        return mapper.map(product, ProductDto.class);

    }


}
