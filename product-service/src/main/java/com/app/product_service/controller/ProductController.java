package com.app.product_service.controller;

import com.app.product_service.models.Product;
import com.app.product_service.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create")
    public Product addProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
    @GetMapping("/get")
    public List<Product> getAllProduct()
    {
        return productRepository.findAll();
    }
    @GetMapping("/getbyId")
    public ResponseEntity<Product>getProductById(@PathVariable Long id) {
        return new ResponseEntity<Product>(
                productRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Data not Found")),
                HttpStatus.OK
        );
    }
}


