package com.product.controller;

import com.product.dto.ApiResponse;
import com.product.dto.CategoryDto;
import com.product.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private CategoryService categoryService;

    public ProductController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list/categories")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getCategories(){

        List<CategoryDto> categoriesDto = categoryService.findAll();


        ApiResponse<List<CategoryDto>> response = new ApiResponse<>();

        if (categoriesDto != null && !categoriesDto.isEmpty()){
            response.setMessage("All categories data fatched");
            response.setStatus(200);
            response.setData(categoriesDto);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }
        response.setMessage("categories data not fatched");
        response.setStatus(400);
        response.setData(null);


        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }


    @GetMapping("/id/categories")
    public  ResponseEntity<ApiResponse<CategoryDto>> getCategoriesById(){

        CategoryDto byCategoryId = categoryService.findByCategoryId(1l);
        ApiResponse<CategoryDto> response = new ApiResponse<>();

        if (byCategoryId !=null ){
            response.setMessage("Fatched by id");
            response.setStatus(200);
            response.setData(byCategoryId);
            return  new ResponseEntity<>(response,HttpStatus.OK);



        }
        response.setMessage(" not Fatched by id");
        response.setStatus(400);
        response.setData(byCategoryId);
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);


    }

    @GetMapping("/name/categories")
    public ResponseEntity<ApiResponse<CategoryDto>> getCategoriesByName(){

        CategoryDto byCategoriesName = categoryService.findByCategoryName("Alice");

        ApiResponse<CategoryDto> response = new ApiResponse<>();

        if (byCategoriesName !=null){
            response.setMessage("Found by name here ");
            response.setStatus(200);
            response.setData(byCategoriesName);
            return  new ResponseEntity<>(response,HttpStatus.OK);



        }
        response.setMessage(" not Found by name here ");
        response.setStatus(400);
        response.setData(byCategoriesName);
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }
}
