package com.onthepitch.backend.soccerApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientServiceImpl implements RestClientService {
    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public RestClientServiceImpl() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.add("Content-Type", "application/json");
        this.headers.add("Accept", "*/*");
    }


    @Autowired
    public void setTokenProviderService(TokenProviderService tokenProviderService) {
        this.headers.add("X-Auth-Token", tokenProviderService.getToken());
    }

    @Override
    public String get(String url) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @Override
    public String post(String url, String json) {
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @Override
    public void put(String url, String json) {
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
    }

    @Override
    public void delete(String url) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
