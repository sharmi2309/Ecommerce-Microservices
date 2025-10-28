package com.app.order_service.controller;

import com.app.order_service.dto.OrderResponseDTO;
import com.app.order_service.dto.ProductDTO;
import com.app.order_service.models.Order;
import com.app.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public OrderController(OrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.webClientBuilder = webClientBuilder;
    }
    @PostMapping("/placeOrder")
    public Mono<ResponseEntity<OrderResponseDTO>> placeOrder(@RequestBody Order order)
    {
        return webClientBuilder.build().get().uri("http://localhost:8083/products/" + order.getProductId()).retrieve()
                .bodyToMono(ProductDTO.class).map(productDTO -> {
                    OrderResponseDTO responseDTO = new OrderResponseDTO();
                    responseDTO.setOrderId(order.getId());
                    responseDTO.setQuantity(order.getQuantity());
                    responseDTO.setProductId(order.getProductId());
                    responseDTO.setProductName(productDTO.getName());
                    responseDTO.setTotalPrice(productDTO.getPrice());
                    responseDTO.setTotalPrice(order.getQuantity()* productDTO.getPrice());
                    orderRepository.save(order);
                    return ResponseEntity.ok(responseDTO);

                });
    }
    @GetMapping
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
