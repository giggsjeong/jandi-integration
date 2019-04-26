package com.jandi.integration.config;


import com.jandi.integration.hmac.HmacAccessFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class WebConfig {


    @Autowired
    private HmacAccessFilter hmacAccessFilter;

    @Bean
    public FilterRegistrationBean<HmacAccessFilter> getFilterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(hmacAccessFilter);
        bean.setUrlPatterns(Arrays.asList("/sample/echo"));
        return bean;
    }
}
