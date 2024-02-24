package com.ecommerce_api.controller;

import com.ecommerce_api.dto.user.SignInDto;
import com.ecommerce_api.dto.user.SignInResponseDto;
import com.ecommerce_api.dto.user.SignUpResponseDto;
import com.ecommerce_api.dto.user.SignupDto;
import com.ecommerce_api.exceptions.AuthenticationFailException;
import com.ecommerce_api.exceptions.CustomException;
import com.ecommerce_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public SignUpResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }
    @PostMapping("/signIn")
    public SignInResponseDto SignIn(@RequestBody SignInDto signInDto) throws CustomException, AuthenticationFailException {
        return userService.signIn(signInDto);
    }

}
