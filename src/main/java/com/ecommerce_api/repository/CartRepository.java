package com.ecommerce_api.repository;

import com.ecommerce_api.entity.Cart;
import com.ecommerce_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
    Optional<Cart> findById(Long id);

}
