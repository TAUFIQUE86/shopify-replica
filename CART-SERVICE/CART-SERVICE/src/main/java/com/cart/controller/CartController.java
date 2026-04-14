package com.cart.controller;

import com.cart.dto.AddToCartRequest;
import com.cart.dto.ApiResponse;
import com.cart.dto.OrderDto;
import com.cart.entity.Cart;
import com.cart.service.CartService;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<OrderDto>> checkout(
          @RequestParam Long userId,
          @RequestBody List<AddToCartRequest> cartitems
          ){
      OrderDto orderDto = cartService.cheakOut(userId, cartitems);
      ApiResponse<OrderDto> response = new ApiResponse<>();
      if (orderDto !=null){
          response.setData(orderDto);
          response.setMessage("Oder created successfully");
          response.setStatus(HttpStatus.SC_OK);

          return  new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.SC_OK));

      }
      response.setData(orderDto);
      response.setMessage("Oder not created here ");
      response.setStatus(HttpStatus.SC_BAD_REQUEST);

      return  new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.SC_BAD_REQUEST));

  }
}
