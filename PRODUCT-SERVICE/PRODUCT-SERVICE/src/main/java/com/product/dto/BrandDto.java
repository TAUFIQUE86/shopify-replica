package com.product.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    @NotNull(message = "id is required")
    private Long id;

    @NotBlank(message = " brand name is required ")
    @Size(min = 3, max = 50,message = "name should be minimum 3 and maximum 50")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @Valid
    @NotBlank(message = "sive is required")
    private Set<SizeDto> sizes = new LinkedHashSet<>();

    @Valid
    @NotBlank(message = "images is required ")
    private Set<ImageDto> images = new LinkedHashSet<>();
}