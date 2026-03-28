package com.product.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ApiResponse<T> {
    private  String message;
    private  Integer status;
    private  T data;

}
