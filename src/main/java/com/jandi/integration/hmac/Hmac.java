package com.jandi.integration.hmac;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Hmac {

    public static String ACCESS_KEY;

    public static String SECURE_KEY;

    public static String SECURE_ALGORITHM ;

    public static String SECURE_FORMAT;

    @Value("${hmac.access.key}")
    public void setAccessKey(String accessKey){
        this.ACCESS_KEY = accessKey;
    }
    @Value("${hmac.secure.key}")
    public void setSecureKey(String secureKey){
        this.SECURE_KEY = secureKey;
    }
    @Value("${hmac.secure.algorithm}")
    public void setAlgorithm(String algorithm){
        this.SECURE_ALGORITHM = algorithm;
    }
    @Value("${hmac.secure.format}")
    public void setFormat(String format){
        this.SECURE_FORMAT = format;
    }
}
