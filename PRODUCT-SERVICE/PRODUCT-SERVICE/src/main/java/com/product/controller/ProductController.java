package com.product.controller;

import com.product.dto.ApiResponse;
import com.product.dto.CategoryDto;
import com.product.dto.ProductDto;
import com.product.service.CategoryService;
import com.product.service.ProductService;
import com.product.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final S3Service s3Service;

    public ProductController(CategoryService categoryService, ProductService productService, S3Service s3Service) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.s3Service = s3Service;
    }


//    http://localhost:8081/api/v1/product/list/categories

    @GetMapping("/list/categories")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getCategories() {

        List<CategoryDto> categoriesDto = categoryService.findAll();


        ApiResponse<List<CategoryDto>> response = new ApiResponse<>();

        if (categoriesDto != null && !categoriesDto.isEmpty()) {
            response.setMessage("All categories data fatched");
            response.setStatus(200);
            response.setData(categoriesDto);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        response.setMessage("categories data not fatched");
        response.setStatus(400);
        response.setData(null);


        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }


    // http://localhost:8081/api/v1/product/list/search?keyword=apple

    @GetMapping("/list/search")
    public ResponseEntity<ApiResponse<List<ProductDto>>> searchProduct(
            @RequestParam String keyword) {


        List<ProductDto> productDtoList = productService.searchProduct(keyword);
        ApiResponse<List<ProductDto>> response = new ApiResponse<>();

        if (productDtoList != null && !productDtoList.isEmpty()) {

            response.setMessage("Data fatched ");
            response.setData(productDtoList);
            response.setStatus(HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);

        }

        response.setMessage("Data  Not fatched ");
        response.setData(null);
        response.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);


    }


    // http://localhost:8081/api/v1/product/list/upload
    @PostMapping("/list/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile[] files) throws IOException {
        ArrayList<String> imagePaths = new ArrayList<>();

        for (MultipartFile file : files) {


            if (file == null || file.isEmpty()) continue;

            String url = s3Service.uploadFile(file);
            imagePaths.add(url);

        }

        return ResponseEntity.ok(Map.of(
                "message", "Image uploaded successfully",
                "urls", imagePaths
        ));

    }

}
