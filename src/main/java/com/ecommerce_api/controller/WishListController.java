package com.ecommerce_api.controller;

import com.ecommerce_api.common.ApiResponse;
import com.ecommerce_api.dto.product.ProductDto;
import com.ecommerce_api.entity.Product;
import com.ecommerce_api.entity.User;
import com.ecommerce_api.entity.WishList;
import com.ecommerce_api.exceptions.AuthenticationFailException;
import com.ecommerce_api.repository.ProductRepository;
import com.ecommerce_api.services.AuthenticationService;
import com.ecommerce_api.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListService wishListService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ProductRepository productRepository;

    /*
    API to add a new product in wishlist
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody ProductDto productDto, @RequestParam("token") String token) throws AuthenticationFailException {
        // first authenticate if the token is valid
        authenticationService.authenticate(token);
        // then fetch the user linked to the token
        User user = authenticationService.getUser(token);

        Product product = productRepository.getById(productDto.getId());

        // save wish list
        WishList wishList = new WishList(user, product);
        wishListService.createWishlist(wishList);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to wishlist"), HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) throws AuthenticationFailException {
        // first authenticate if the token is valid
        authenticationService.authenticate(token);
        // then fetch the user linked to the token
        User user = authenticationService.getUser(token);
        // first retrieve the wishlist items
        List<WishList> wishLists = wishListService.readWishList(user);

        List<ProductDto> products = new ArrayList<>();
        for (WishList wishList : wishLists) {
            // change each product to product DTO
            products.add(new ProductDto(wishList.getProduct()));
        }
        // send the response to user
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
