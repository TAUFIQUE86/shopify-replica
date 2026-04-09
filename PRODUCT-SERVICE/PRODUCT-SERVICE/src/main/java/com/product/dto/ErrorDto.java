package com.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDto {

    @NotNull(message = "Date cannot be null.")
    private Date date;

    @NotBlank(message = "Message cannot be empty.")
    private  String message;

    @NotBlank(message = "URL cannot be empty.")
//    @URL(message = "URL format is invalid.")
    private String url;
}
