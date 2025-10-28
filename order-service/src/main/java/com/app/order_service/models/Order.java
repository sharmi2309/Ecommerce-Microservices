package com.app.order_service.models;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name ="orders")
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long productId;
        private Integer quantity;


}
