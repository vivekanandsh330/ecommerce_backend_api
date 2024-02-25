package com.ecommerce_api.services;

import com.ecommerce_api.dto.cart.AddToCartDto;
import com.ecommerce_api.dto.cart.CartDto;
import com.ecommerce_api.dto.cart.CartItemDto;
import com.ecommerce_api.entity.Cart;
import com.ecommerce_api.entity.Product;
import com.ecommerce_api.entity.User;
import com.ecommerce_api.exceptions.CustomException;
import com.ecommerce_api.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, User user) {

        // validate if the product id is valid
        Product product = productService.findById(addToCartDto.getProductId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());


        // save the cart
        cartRepository.save(cart);

    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart: cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItems.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);
        return  cartDto;
    }

    public void deleteCartItem(Integer cartItemId, User user) throws CustomException {
        // the item id belongs to user

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }

        Cart cart = optionalCart.get();

        if (cart.getUser() != user) {
            throw  new CustomException("cart item does not belong to user: " +cartItemId);
        }

        cartRepository.delete(cart);

    }
    public void updateCartItemQuantity(Long cartItemId, int quantity, User user) {
        Optional<Cart> cartItemOptional = cartRepository.findById(cartItemId);

        if (cartItemOptional.isPresent()) {
            Cart cartItem = cartItemOptional.get();

            // Ensure the cart item belongs to the user before updating
            if (cartItem.getUser().equals(user)) {
                // Ensure the quantity is positive
                if (quantity > 0) {
                    cartItem.setQuantity(quantity);
                    cartRepository.save(cartItem);
                } else {
                    throw new IllegalArgumentException("Quantity must be greater than zero");
                }
            } else {
                throw new IllegalArgumentException("Cart item does not belong to the current user");
            }
        } else {
            throw new NoSuchElementException("Cart item not found");
        }
    }
    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
}
