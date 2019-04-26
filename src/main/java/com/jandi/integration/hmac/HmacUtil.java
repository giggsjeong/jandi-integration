package com.jandi.integration.hmac;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HmacUtil {

    public static AuthorizationHeader getAuthHeader(String pattern, String authorization) {

        Matcher authHeaderMatcher = Pattern.compile(pattern)
                                            .matcher(Optional.ofNullable(authorization)
                                            .orElse(""));

        if (!authHeaderMatcher.matches()) {
            // invalid authorization token
           return null;
        }

        final String algorithm = authHeaderMatcher.group(1);
        final String apiKey = authHeaderMatcher.group(2);
        final String signature = authHeaderMatcher.group(3);

        return new AuthorizationHeader(algorithm,apiKey,signature);
    }


    /**
     * Signature 생성
     *
     * @param secretAccessKey Private key
     * @param verb Http method
     * @param url Api url
     * @param date Created date
     * @return Signature
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String createSignature(String algorithm,String secretAccessKey, String verb, String url,byte[] payload, String date)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String stringToSign = createSign(verb, url,payload, date);
        Mac sha256_HMAC = Mac.getInstance(algorithm);
        SecretKeySpec secret_key = new SecretKeySpec(secretAccessKey.getBytes(), algorithm);
        sha256_HMAC.init(secret_key);

        return Base64.encodeBase64String(sha256_HMAC.doFinal(stringToSign.getBytes()));
    }
    private static String createSign(String verb, String url,byte[] payload, String date) throws UnsupportedEncodingException {
        String stringToSign = String.format("%s\n%s\n%s\n%s", verb, url,new String(payload), date);
        return new String(stringToSign.getBytes("UTF-8"));
    }
}
