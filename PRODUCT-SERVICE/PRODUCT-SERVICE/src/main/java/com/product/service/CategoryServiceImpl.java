package com.product.service;

import com.product.dto.CategoryDto;
import com.product.entity.Category;
import com.product.mapper.CategoryMapper;
import com.product.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements  CategoryService{

    private final CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryRepository categoryRepository
                              ) {
        this.categoryRepository = categoryRepository;

    }

    @Override
    public List<CategoryDto> findAll() {

        List<Category> categories = categoryRepository.findAll();

        List<CategoryDto> dtoList = new ArrayList<>();

        for (Category c : categories){

            CategoryDto categoryDto = CategoryMapper.convertCategoryDto(c);
            dtoList.add(categoryDto);


        }
        return  dtoList;
    }

    /*


    return categoryRepository.findAll()
            .stream()
            .map(CategoryMapper::convertCategoryDto)
            .collect(Collectors.toList());
     */

/*
    @Override
    public CategoryDto findByCategoryId(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::convertCategoryDto)
                .orElse(null);
    }

    @Override
    public CategoryDto findByCategoryName(String name) {
        return categoryRepository.findByNameIgnoreCase(name)
                .map(CategoryMapper::convertCategoryDto)
                .orElse(null);
    }



 */
}
