package com.gate.zml.config;

import com.gate.zml.filter.JwtCheckGatewayFilterFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public JwtCheckGatewayFilterFactory jwtCheckGatewayFilterFactory(){
        return new JwtCheckGatewayFilterFactory();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}