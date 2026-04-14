package com.order.service;

import com.order.dto.*;
import com.order.entity.Order;
import com.order.entity.OrderItem;
import com.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // ✅ CREATE ORDER
    public ApiResponse<OrderDto> createOrder(OrderDto request) {

        // 1️⃣ Convert DTO → Entity
        Set<OrderItem> orderItems = new LinkedHashSet<>();

        for (OrderItemDto itemDto : request.getItems()) {
            OrderItem item = new OrderItem();
            item.setProductId(itemDto.getProductId());
            item.setBrandId(itemDto.getBrandId());
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(itemDto.getPrice());

            orderItems.add(item);
        }

        // 2️⃣ Calculate total amount
        BigDecimal totalAmount = orderItems.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3️⃣ Create Order entity
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setCartUuid(request.getCartUuid());
        order.setStatus("CREATED");
        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItems);

        // 4️⃣ Set relationship (VERY IMPORTANT 🔥)
        orderItems.forEach(item -> item.setOrder(order));

        // 5️⃣ Save to DB
        Order savedOrder = orderRepository.save(order);

        // 6️⃣ Convert Entity → DTO
        OrderDto responseDto = mapToDto(savedOrder);

        return new ApiResponse<>("Order created successfully", 201, responseDto);
    }

    // 🔁 Mapping Method
    private OrderDto mapToDto(Order order) {
        return OrderDto.builder()
                .orderId(order.getId())
                .cartUuid(order.getCartUuid())
                .userId(order.getUserId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreateAt())
                .items(order.getOrderItems().stream()
                        .map(item -> OrderItemDto.builder()
                                .productId(item.getProductId())
                                .brandId(item.getBrandId())
                                .quantity(item.getQuantity())
                                .price(item.getPrice())
                                .build()
                        ).collect(Collectors.toSet()))
                .build();
    }
}