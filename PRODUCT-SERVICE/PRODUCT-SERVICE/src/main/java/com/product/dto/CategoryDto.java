package com.product.dto;

import jakarta.validation.constraints.*;
import jakarta.annotation.Nonnull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {
    @NotNull(message = "id is required ")
    private Long id;
    @NotBlank(message = "category name is required ")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
}
