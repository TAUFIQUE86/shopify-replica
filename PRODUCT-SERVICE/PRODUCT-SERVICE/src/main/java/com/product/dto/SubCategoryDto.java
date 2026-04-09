package com.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDto {
     @NotNull(message = "id is required")
    private Long id;
    @NotBlank(message = "Subcategory is required ")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
}