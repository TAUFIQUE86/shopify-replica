package com.product;


import com.product.controller.ProductController;
import com.product.dto.ApiResponse;
import com.product.dto.CategoryDto;
import com.product.dto.ProductDto;
import com.product.service.CategoryService;
import com.product.service.ProductService;
import com.product.service.S3Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CategoryService categoryService;

    @MockitoBean
    private ProductService productService;
    @MockitoBean
    private S3Service s3Service;

    @Test
    void getCategories_ShouldReturn200_WhenCategoriesExist() throws Exception {

        // Arrange - prepare fake data
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("Electronics");

        List<CategoryDto> categoryList = List.of(categoryDto);

        // Mock service

        when(categoryService.findAll()).thenReturn(categoryList);


        // Act + Assert

        mockMvc.perform(get("/api/v1/product/list/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("All categories data fatched"))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Electronics"));

        // Verify service was called once
        verify(categoryService, times(1)).findAll();

    }
}
