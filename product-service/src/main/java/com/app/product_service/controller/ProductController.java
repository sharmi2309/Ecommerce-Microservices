package com.app.product_service.controller;

import com.app.product_service.models.Product;
import com.app.product_service.repository.ProductRepository;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping("/{id}")
    public ResponseEntity<Product>getProductById(@PathVariable Long id) {
        return new ResponseEntity<Product>(
                productRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Data not Found")),
                HttpStatus.OK
        );
    }
}


