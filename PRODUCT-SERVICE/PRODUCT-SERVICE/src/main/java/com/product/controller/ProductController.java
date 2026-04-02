package com.product.controller;

import com.product.dto.ApiResponse;
import com.product.dto.CategoryDto;
import com.product.dto.ProductDto;
import com.product.service.CategoryService;
import com.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }



//    http://localhost:8081/api/v1/product/list/categories

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

    /*
// my practise
// http://localhost:8081/api/v1/product/1/categories

    @GetMapping("/{id}/categories")
    public  ResponseEntity<ApiResponse<CategoryDto>> getCategoriesById(@PathVariable  Long id){

        CategoryDto byCategoryId = categoryService.findByCategoryId(id);
        ApiResponse<CategoryDto> response = new ApiResponse<>();

        if (byCategoryId !=null){
            response.setMessage("Fatched by id");
            response.setStatus(HttpStatus.OK.value());
            response.setData(byCategoryId);
            return  new ResponseEntity<>(response,HttpStatus.OK);



        }
        response.setMessage(" not Fatched by id");
        response.setStatus(400);
        response.setData(null);
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);


    }

// my practise
    // http://localhost:8081/api/v1/product/name/categories?name=Electronics

    @GetMapping("/name/categories")
    public ResponseEntity<ApiResponse<CategoryDto>> getCategoriesByName(@RequestParam String name){

        CategoryDto byCategoriesName = categoryService.findByCategoryName(name);

        ApiResponse<CategoryDto> response = new ApiResponse<>();

        if (byCategoriesName !=null){
            response.setMessage("Found by name here ");
            response.setStatus(HttpStatus.OK.value());
            response.setData(byCategoriesName);
            return  new ResponseEntity<>(response,HttpStatus.OK);



        }
        response.setMessage(" not Found by name here ");
        response.setStatus(400);
        response.setData(null);
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }




     */



    // http://localhost:8081/api/v1/product/list/search?keyword=apple

    @GetMapping("/list/search")
    public  ResponseEntity<ApiResponse<List<ProductDto>>> searchProduct(
           @RequestParam String keyword){


        List<ProductDto> productDtoList = productService.searchProduct(keyword);
        ApiResponse<List<ProductDto>> response = new ApiResponse<>();

        if (productDtoList != null && !productDtoList.isEmpty()){

            response.setMessage("Data fatched ");
            response.setData(productDtoList);
            response.setStatus(HttpStatus.OK.value());
            return  new ResponseEntity<>(response,HttpStatus.OK);

        }

        response.setMessage("Data  Not fatched ");
        response.setData(null);
        response.setStatus(HttpStatus.NOT_FOUND.value());

        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);


    }
}
