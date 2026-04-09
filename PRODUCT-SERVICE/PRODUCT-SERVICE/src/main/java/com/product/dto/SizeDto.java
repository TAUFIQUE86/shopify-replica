package com.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SizeDto {
    @NotNull(message = "id is required ")
    private Integer id;

    @NotBlank(message = "size is required ")
    private String size;

    @NotBlank(message = "quantity is required ")
    private String quantity;
}