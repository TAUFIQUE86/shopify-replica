package com.product.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Set<SizeDto> sizes = new LinkedHashSet<>();
    private Set<ImageDto> images = new LinkedHashSet<>();
}