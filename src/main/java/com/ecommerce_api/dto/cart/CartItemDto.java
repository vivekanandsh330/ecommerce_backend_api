package com.ecommerce_api.dto.cart;

import com.ecommerce_api.entity.Cart;
import com.ecommerce_api.entity.Product;
import com.ecommerce_api.entity.User;

public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;
    private User user;

    public CartItemDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CartItemDto(Cart cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.product = cart.getProduct();
        this.user = cart.getUser();
    }
}
