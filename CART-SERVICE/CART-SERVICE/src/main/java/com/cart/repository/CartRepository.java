package com.cart.repository;

import com.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    <Optional>Cart findByUuid(String uuid);
}