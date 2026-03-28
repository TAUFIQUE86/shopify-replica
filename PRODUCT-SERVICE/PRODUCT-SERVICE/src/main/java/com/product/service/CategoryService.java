package com.product.service;


import com.product.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    public List<CategoryDto> findAll();
    public CategoryDto findByCategoryId(Long id);
    public  CategoryDto findByCategoryName(String name);

}
