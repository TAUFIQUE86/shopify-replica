package com.cart.client;

import com.cart.dto.AddToCartRequest;
import com.cart.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ORDER-SERVICE", url = "http://localhost:8082")
public interface OrderClient {
    @PostMapping("/api/orders")
   public OrderDto createOrder(
            @RequestParam Long userId,
            @RequestBody List<AddToCartRequest> cartItems
    );
}
