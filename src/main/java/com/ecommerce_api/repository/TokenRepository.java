package com.ecommerce_api.repository;

import com.ecommerce_api.entity.AuthenticationToken;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer>

    {
        AuthenticationToken findTokenByUser(User user);
        AuthenticationToken findTokenByToken(String token);
}
