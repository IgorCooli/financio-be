package com.io.financio.config.security;

import com.io.financio.config.security.dataprovider.ValidateSessionDataProvider;
import com.io.financio.domain.service.criptography.RsaDecryptService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class AuthFilter extends GenericFilterBean {

    private final RsaDecryptService decryptService;
    private final ValidateSessionDataProvider sessionDataProvider;

    public AuthFilter(RsaDecryptService decryptService, ValidateSessionDataProvider sessionDataProvider) {
        this.decryptService = decryptService;
        this.sessionDataProvider = sessionDataProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final var request = (HttpServletRequest) servletRequest;
        final var response = (HttpServletResponse) servletResponse;
        final var authHeader = request.getHeader("authorization");

        if (authHeader == null) {
            //TODO trocar ex
            throw new ServletException("An exception occurred");
        }
        var sessionId = decryptService.execute(authHeader);

        try {
            sessionDataProvider.execute(sessionId);
        } catch (Exception e) {
            //TODO TROCAR EXCEPTION
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
    }
}
