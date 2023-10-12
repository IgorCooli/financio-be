package com.io.financio.config.security;

import com.io.financio.config.security.dataprovider.ValidateSessionDataProvider;
import com.io.financio.domain.exception.AuthTokenNotReceivedException;
import com.io.financio.domain.exception.SessionNotFoundException;
import com.io.financio.domain.service.criptography.RsaDecryptService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
@Component
public class AuthFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "authorization";
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
        final var authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authHeader.isBlank()) {
            throw new AuthTokenNotReceivedException("auth token was not received");
        }

        var sessionId = decryptService.execute(authHeader);

        try {
            sessionDataProvider.execute(sessionId);
        } catch (SessionNotFoundException ex) {
            log.error("m=doFilter, msg={}", ex.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);
    }
}
