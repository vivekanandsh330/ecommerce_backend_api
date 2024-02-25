package com.ecommerce_api.controller;

import com.ecommerce_api.common.ApiResponse;
import com.ecommerce_api.dto.cart.AddToCartDto;
import com.ecommerce_api.dto.cart.CartDto;
import com.ecommerce_api.entity.User;
import com.ecommerce_api.exceptions.AuthenticationFailException;
import com.ecommerce_api.exceptions.CustomException;
import com.ecommerce_api.services.AuthenticationService;
import com.ecommerce_api.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;


    // post cart api
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) throws AuthenticationFailException {
        // authenticate the token
        authenticationService.authenticate(token);


        // find the user

        User user = authenticationService.getUser(token);


        cartService.addToCart(addToCartDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }


    // get all cart items for a user
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        // get cart items

        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    // delete a cart item for a user

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                      @RequestParam("token") String token) throws AuthenticationFailException, CustomException {

        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        cartService.deleteCartItem(itemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }
    // update cart item quantity for a user
    @PostMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable("cartItemId") Integer itemId,
                                                      @RequestParam("quantity") Integer quantity,
                                                      @RequestParam("token") String token) throws AuthenticationFailException {
        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        // update cart item quantity
        cartService.updateCartItemQuantity(Long.valueOf(itemId), quantity, user);

        return new ResponseEntity<>(new ApiResponse(true, "Cart item quantity updated"), HttpStatus.OK);
    }

}
