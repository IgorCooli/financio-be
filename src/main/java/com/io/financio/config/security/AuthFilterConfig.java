package com.io.financio.config.security;

import com.io.financio.entrypoint.constants.RestConstants;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthFilterConfig {

    private final AuthFilter authFilter;

    public AuthFilterConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration() {

        var filter= new FilterRegistrationBean<AuthFilter>();
        filter.setFilter(authFilter);

        filter.addUrlPatterns(RestConstants.BASE_API + "/*");
        filter.addInitParameter("exclusions", RestConstants.AUTHENTICATE_URL);
        filter.addInitParameter("exclusions", RestConstants.REGISTER_URL);
        return filter;
    }
}
