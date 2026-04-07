package com.cart.controller;

import com.cart.dto.AddToCartRequest;
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
    public ResponseEntity<String> addToCart(
            @RequestHeader(value = "X-CART-ID", required = false) String uuid,
            @RequestBody AddToCartRequest request
    ) {

        Cart cart = cartService.addToCart(uuid, request);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CART-ID", cart.getUuid());

        return ResponseEntity.ok()
                .headers(headers)
                .body("Product add  to the cart");

    }
}
