package com.product.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NotNull(message = "id is required")
    private Long id;
    @NotBlank(message = "product name will be required")
    @Size(min = 3, max = 50 , message = "Name should be minimum 3 and maximum 50 letter")
    private String name;
    @Valid
    @NotBlank(message = "subcategory will be required ")
    private SubCategoryDto subCategory;

    @Valid
    @NotEmpty(message = "brands is required")
    private Set<BrandDto> brands = new LinkedHashSet<>();
}
