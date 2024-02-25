package com.ecommerce_api.controller;

import com.ecommerce_api.common.ApiResponse;
import com.ecommerce_api.dto.product.ProductDto;
import com.ecommerce_api.entity.Category;
import com.ecommerce_api.services.CategoryService;
import com.ecommerce_api.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    // list all the products
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productDtos = productService.listProducts();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    // update a product
    @PostMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID,
                                                     @RequestBody @Valid ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productID, productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }
    // Set availability endpoint
    @PostMapping("/availability/{productID}")
    public ResponseEntity<ApiResponse> setProductAvailability(@PathVariable("productID") Integer productID,
                                                              @RequestParam boolean availability) {
        productService.setProductAvailability(productID, availability);
        return new ResponseEntity<>(new ApiResponse(true, "Product availability has been updated"), HttpStatus.OK);
    }

    // Update availability endpoint
    @PutMapping("/availability/{productID}")
    public ResponseEntity<ApiResponse> updateProductAvailability(@PathVariable("productID") Integer productID,
                                                                 @RequestParam boolean availability) {
        productService.updateProductAvailability(productID, availability);
        return new ResponseEntity<>(new ApiResponse(true, "Product availability has been updated"), HttpStatus.OK);
    }
}