package com.cart.service;

import com.cart.client.OrderClient;
import com.cart.dto.AddToCartRequest;
import com.cart.dto.OrderDto;
import com.cart.entity.Cart;
import com.cart.entity.CartItem;
import com.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class CartService {

    private final CartRepository cartRepository;
    private final OrderClient orderClient;

    public CartService(CartRepository cartRepository, OrderClient orderClient) {
        this.cartRepository = cartRepository;
        this.orderClient = orderClient;
    }

    public Cart addToCart(String uuid, AddToCartRequest request) {

        Cart cart;

        if (uuid == null || uuid.isEmpty()) {
            // 1. If no UUID is provided, create a brand_new Cart and ID
            String newUuid = UUID.randomUUID().toString();
            cart = new Cart();
            cart.setUuid(newUuid);
        } else {
            // 2. If a UUID is provided, try to find the existing cart
            cart = cartRepository.findByUuid(uuid);

            // 3. If the UUID was provided but not found in DB, create it
            if (cart == null) {
                cart = new Cart();
                cart.setUuid(uuid);
            }
        }
        // 2. Check if product already exists

        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProductId()
                        .equals(request.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            // here add the previous and new added quantity
            item.setQuantity(item.getQuantity() + request.getQuantity());

        } else {
            // here create new cartitem, and  item added inside cartitem
            CartItem newItem = new CartItem();
            newItem.setProductId(request.getProductId());
            newItem.setBrandId(request.getBrandId());
            newItem.setQuantity(request.getQuantity());
            newItem.setPrice(request.getPrice());
            // set  new item to the cart
            newItem.setCart(cart);
            //   here you will see your added item
            cart.getCartItems().add(newItem);
        }

        // finaly save item inside cart
        return cartRepository.save(cart);
    }


    // feignClient calling from orderclient
    public OrderDto cheakOut(Long userId, List<AddToCartRequest> cartItems) {

      //  Call Order Service
        return orderClient.createOrder(userId, cartItems);
    }

}
