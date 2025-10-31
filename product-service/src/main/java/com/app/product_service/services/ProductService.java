package com.app.product_service.services;

import com.app.product_service.models.Product;
import com.app.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private final Supplier<List<Product>> defaultProductSupplier = () -> List.of(
            new Product() {{ setId(0L); setName("Default Product 1"); setPrice(0.0); }},
            new Product() {{ setId(0L); setName("Default Product 2"); setPrice(0.0); }}
    );
    private final Consumer<Product> logProductConsumer =
            (product) -> System.out.println("Logging Product: " + product.getName() + " | Price: " + product.getPrice());


    public List<String> getProcessedProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            products = defaultProductSupplier.get();
        }
        products.forEach(logProductConsumer);
        return products.stream()
                .filter(p -> p.getPrice() >= 1000)
                .map(p -> p.getName().toUpperCase())
                .collect(Collectors.toList());
    }
}
