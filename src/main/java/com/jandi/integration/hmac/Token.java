package com.jandi.integration.hmac;


import com.jandi.integration.exception.InvalidTokenException;

public interface Token {

    /**
     * Token 검증
     *
     * 검증 실패시 {@link InvalidTokenException} 발생시킴
     *
     * @param secretAccessKey Private Key
     */
    void verify(String algorithm,String secretAccessKey);
}
