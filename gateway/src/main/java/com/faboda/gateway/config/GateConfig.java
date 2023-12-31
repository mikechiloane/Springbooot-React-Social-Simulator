package com.faboda.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class GateConfig {


        @Bean
        public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
            return builder.routes()
                    .route("user", r -> r.path("/v1/user/**")
                            .uri("https://user-service-u1f1.onrender.com"))
                    .route("location", r -> r.path("/v1/location/**")
                            .uri("https://location-service.onrender.com/v1/location"))
                    .route("messaging", r -> r.path("/v1/message/**")
                            .uri("https://messaging-service-1mwa.onrender.com"))
                    .route("websocket_route", r -> r.path("/location/**")
                            .uri("https://location-service.onrender.com"))
                    .build();
        }

}
