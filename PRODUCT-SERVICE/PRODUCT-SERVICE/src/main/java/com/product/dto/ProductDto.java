package com.product.dto;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private SubCategoryDto subCategory;
    private Set<BrandDto> brands = new LinkedHashSet<>();
}
