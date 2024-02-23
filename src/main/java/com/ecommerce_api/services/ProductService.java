package com.ecommerce_api.services;

import com.ecommerce_api.common.ProductDto;
import com.ecommerce_api.entity.Category;
import com.ecommerce_api.entity.Product;
import com.ecommerce_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        return product;
    }
    // add the products
    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }
    // list of all the products
    public List<ProductDto> listProducts() {
        // first fetch all the products
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : products) {
            // for each product change it to DTO
            productDtos.add(new ProductDto(product));
        }
        return productDtos;
    }

    // update a product
    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        // set the id for updating
        product.setId(productID);
        // update
        productRepository.save(product);
    }
    // Set product availability
    public void setProductAvailability(Integer productId, boolean availability) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setAvailability(availability);
            productRepository.save(product);
        }
    }

    // Update product availability
    public void updateProductAvailability(Integer productId, boolean availability) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setAvailability(availability);
            productRepository.save(product);
        }
    }
}
