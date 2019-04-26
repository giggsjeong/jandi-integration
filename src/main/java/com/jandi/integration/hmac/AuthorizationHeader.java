package com.jandi.integration.hmac;

import lombok.Data;

@Data
public class AuthorizationHeader {

    private final String algorithm;
    private final String apiKey;
    private final String signature;

    public AuthorizationHeader(String algorithm, String apiKey, String signature) {
        this.algorithm = algorithm;
        this.apiKey = apiKey;
        this.signature = signature;
    }
}
