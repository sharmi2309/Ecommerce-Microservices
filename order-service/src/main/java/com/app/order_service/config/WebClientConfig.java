package com.app.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    //This Config is Used for Communication b/w services(From Order to Product)
    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
}
