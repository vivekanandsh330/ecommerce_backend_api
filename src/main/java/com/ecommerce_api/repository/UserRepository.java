package com.ecommerce_api.repository;

import com.ecommerce_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Additional custom queries can be defined here if needed
    User findByEmail(String email);
}
