package com.app.product_service.controller;

import com.app.product_service.models.Product;
import com.app.product_service.repository.ProductRepository;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.slf4j.Logger;
@RestController
@RequestMapping ("/products")
public class ProductController {
    Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create")
    public Product addProduct(@RequestBody Product product){
        log.info("Product:{}", product);
        return productRepository.save(product);

    }
    @GetMapping("/get")
    public List<Product> getAllProduct()
    {
        log.info("Product List!!");
        log.debug("Debugging the Product List !!");
        return productRepository.findAll();
    }
    private final Supplier<Product> defaultProductSupplier =
            () -> {
                Product defaultProduct = new Product();
                defaultProduct.setId(0L);
                defaultProduct.setName("Product is not Available");
                defaultProduct.setPrice(0.0);
                return defaultProduct;
            };


    private final Consumer<Product> logProductConsumer =
            (product) -> System.out.println("Consumed Product: " + product);

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseGet(defaultProductSupplier);
        logProductConsumer.accept(product);
        return product;
    }
}


