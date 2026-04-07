package com.cart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_item")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  Long productId;
    private  Long brandId;
    private  Integer quantity;
    private BigDecimal price;


    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
