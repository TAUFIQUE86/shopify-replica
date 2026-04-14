package com.order.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long orderId;
    private String cartUuid;
    private String userId;
    private String status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    private Set<OrderItemDto> items;
}
