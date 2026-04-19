package com.api_gateway.filters;

import com.api_gateway.utility.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthFilter implements GlobalFilter {



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        return chain.filter(exchange.mutate()
//                .request(exchange.getRequest())
//                .build());
        String path = exchange.getRequest()
                .getURI()
                .getPath();
        System.out.println(path);
        // Public routes (skip authentication)
        List<String> openEndpoints = List.of(
                "/api/auth/login",
                "/api/auth/signup",
                "/api/courses",
                "/api/courses/files/",
                "/api/users/files/"
        );

        if (openEndpoints.stream().anyMatch(path::contains)) {
            return chain.filter(exchange);
        }

        // Protected routes
        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange);
        }


        String token = authHeader.substring(7);

        try {

            JwtUtil.validateToken(token);

            String userId = JwtUtil.extractUserId(token);

            // Forward user info to downstream services
            ServerHttpRequest modifiedRequest =
                    exchange.getRequest()
                            .mutate()
                            .header("X-User-Id", userId)
                            .build();

            return chain.filter(
                    exchange.mutate()
                            .request(modifiedRequest)
                            .build()
            );

        } catch (Exception e) {
            return unauthorized(exchange);
        }

    }


private Mono<Void> unauthorized(ServerWebExchange exchange) {
    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
    return exchange.getResponse().setComplete();
}
    }



