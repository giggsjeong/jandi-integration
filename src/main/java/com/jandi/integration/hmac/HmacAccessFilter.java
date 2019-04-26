package com.jandi.integration.hmac;

import com.jandi.integration.exception.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class HmacAccessFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



        AuthorizationHeader authHeader = HmacUtil.getAuthHeader(Hmac.SECURE_FORMAT,request.getHeader("Authorization"));
        String dateHeader = request.getHeader("Date");
        String xDateHeader = request.getHeader("X-Date");
        String date = StringUtils.isEmpty(dateHeader) ? xDateHeader : dateHeader;

        if(authHeader == null||date==null|!authHeader.getApiKey().equals(Hmac.ACCESS_KEY)){
           throw new InvalidTokenException("Invalid Authorization Header");
        }

        HmacRequestWrapper requestWrapper = new  HmacRequestWrapper(request);

        byte[] contentAsByteArray = requestWrapper.getContentAsByteArray();

        AuthToken authToken = AuthToken.builder()
                .authorization(authHeader)
                .date(date)
                .payload(contentAsByteArray)
                .url(request.getRequestURI())
                .verb(request.getMethod())
                .build();

        logger.info(authToken.toString());

        authToken.verify(Hmac.SECURE_ALGORITHM,Hmac.SECURE_KEY);

        filterChain.doFilter(requestWrapper, response);

    }
}
