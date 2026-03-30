package com.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Set<SizeDto> sizes = new LinkedHashSet<>();
    private Set<ImageDto> images = new LinkedHashSet<>();
}