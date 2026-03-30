package com.product.mapper;

import com.product.dto.CategoryDto;
import com.product.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


public class CategoryMapper {
    /*

            Applications often consist of similar but different object models,
            where the data in two models may be similar but the structure and concerns of
            the models are different. Object mapping makes it easy to convert one model to
            another, allowing separate models to remain segregated.

            Why ModelMapper?
            The goal of ModelMapper is to make object mapping easy,
             by automatically determining how one object model maps to another,
              based on conventions, in the same way that a human would - while providing a simple,
               refactoring-safe API for handling specific use cases.

     */
    private static final ModelMapper mapper = new ModelMapper();


    public static CategoryDto convertCategoryDto(Category category) {
        return mapper.map(category, CategoryDto.class);

    }

}
