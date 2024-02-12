package com.example.springgooglelogin.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleAuthService {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${spring.oauth2.google.client-id}")
    private String googleClientId;
    @Value("${spring.oauth2.google.client-secret}")
    private String googleClientSecret;
    @Value("${spring.oauth2.google.redirect-uri}")
    private String googleRedirectUri;
    @Value("${spring.oauth2.google.token-uri}")
    private String googleTokenUri;
    @Value("${spring.oauth2.google.resource-uri}")
    private String googleResourceUri;
    public void socialLogin(String code) {
        String accessToken = getAccessToken(code);
        JsonNode userResourceNode = getUserResource(accessToken);
        System.out.println("userResourceNode = " + userResourceNode);

        String id = userResourceNode.get("id").asText();
        String email = userResourceNode.get("email").asText();
        String name = userResourceNode.get("name").asText();
        System.out.println("id = " + id);
        System.out.println("email = " + email);
        System.out.println("name = " + name);
    }
    private String getAccessToken(String authorizationCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", googleClientId);
        params.add("client_secret", googleClientSecret);
        params.add("redirect_uri", googleRedirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(googleTokenUri, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        return accessTokenNode.get("access_token").asText();
    }
    private JsonNode getUserResource(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(googleResourceUri, HttpMethod.GET, entity, JsonNode.class);
        return responseEntity.getBody();
    }
}
