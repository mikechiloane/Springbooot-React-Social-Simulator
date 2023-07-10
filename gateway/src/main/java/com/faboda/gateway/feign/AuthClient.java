package com.faboda.gateway.feign;


import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "auth-server", url = "https://auth-server-meon.onrender.com")
public interface AuthClient {

    @GetMapping("/auth0/validate")
    Response tokenIsValid(@RequestHeader("Authorization") String token);
}
