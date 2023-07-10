package com.faboda.gateway.config;

import com.faboda.gateway.feign.AuthClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class AuthenticationFilter implements GlobalFilter, Ordered {

    private static final int FILTER_ORDER = -1;
    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);
    private final AuthClient authClient;

    public AuthenticationFilter(@Lazy AuthClient authClient) {
        this.authClient = authClient;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        var authHeader = request.getHeaders();

        logger.info("authHeader: " + authHeader);

        boolean isValidToken = validateToken(authHeader);

        if (isValidToken) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    private boolean validateToken(HttpHeaders headers) {

       String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
       if (Objects.isNull(token)) {
           return false;
       }
       Response response = authClient.tokenIsValid(token);
       return response.status() == HttpStatus.OK.value();
    }

    @Override
    public int getOrder() {
        return FILTER_ORDER;
    }
}
