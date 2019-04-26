package com.jandi.integration.hmac;

import com.jandi.integration.exception.InvalidTokenException;
import com.jandi.integration.util.DateTimeUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
@Slf4j
public class AuthToken implements Token{

    private static final int EFFECTIVE_TIME_MINUTE = 5;

    private AuthorizationHeader authorization;

    private String verb;

    private String url;

    private byte[] payload;

    private  String date;


    @Override
    public void verify(String algorithm,String secretAccessKey) {
        try {
            checkTokenExpired();

            String signature = HmacUtil.createSignature(algorithm,secretAccessKey, this.verb, this.url,this.payload ,this.date);

            if (!signature.equals(authorization.getSignature())) {
                throw new InvalidTokenException("Invalid Signature");
            }
        } catch (InvalidTokenException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidTokenException("Token verify error");
        }


    }
    private void checkTokenExpired() {
        LocalDateTime tokenDate = LocalDateTime.parse(this.date, DateTimeFormatter.RFC_1123_DATE_TIME);
        LocalDateTime now = DateTimeUtil.nowGMT();

        if (tokenDate.isBefore(now.minusMinutes(EFFECTIVE_TIME_MINUTE)) ||
                tokenDate.isAfter(now.plusMinutes(EFFECTIVE_TIME_MINUTE))) {
            throw new InvalidTokenException("Token Date Expired");
        }
    }
}
