package com.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    @NotNull(message = "id is required")
    private Long id;

    @NotBlank(message = "url is required ")
    private String url;
}
