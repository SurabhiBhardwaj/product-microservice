package com.example.productMS.service;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class TokenService {

    public String verifyToken(String token) throws RestClientException, IOException {
        String baseUrl = "http://localhost:8089/token/verify";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(token), String.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(response.getBody());
        return response.getBody().toString();
    }

    private static HttpEntity<?> getHeaders(String token) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("token", token);
        return new HttpEntity<>(headers);
    }
}
