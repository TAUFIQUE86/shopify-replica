package com.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {

    private  Long productId;
    private  Long brandId;
    private  Integer quantity;
    private BigDecimal price;


}
