package com.cart.controller;

import com.cart.dto.AddToCartRequest;
import com.cart.dto.ApiResponse;
import com.cart.entity.Cart;
import com.cart.service.CartService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

     // http://localhost:8082/api/v1/cart/add
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Cart>> addToCart(
            @RequestHeader(value = "X-CART-ID", required = false) String uuid,
            @RequestBody AddToCartRequest request
    ) {

        Cart cart = cartService.addToCart(uuid, request);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CART-ID", cart.getUuid());

        ApiResponse<Cart> response = new ApiResponse<>(
                "Product added successfuly",
                200,
                cart


        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(response);

    }
}
