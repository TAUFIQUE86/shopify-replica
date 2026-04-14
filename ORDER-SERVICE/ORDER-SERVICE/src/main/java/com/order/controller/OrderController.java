package com.order.controller;

import com.order.dto.ApiResponse;
import com.order.dto.OrderDto;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderDto> createOrder(@RequestBody OrderDto request) {
        return orderService.createOrder(request);
    }
}