package com.opplus.demouserGitPG.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.opplus.demouserGitPG.filter.CORSFilter;

@Configuration
public class WebConfig {

    @Autowired
    private CORSFilter corsFilter;

    @Bean
    public FilterRegistrationBean<CORSFilter> corsFilter() {
        FilterRegistrationBean<CORSFilter> registration = new FilterRegistrationBean<CORSFilter>();
        registration.setFilter(corsFilter);
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(1);
        return registration;
    }
}