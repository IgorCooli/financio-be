package com.io.financio.domain.usecase.authenticate;

import com.io.financio.domain.dataprovider.authenticate.AuthenticateDataProvider;
import com.io.financio.domain.exception.DigestPasswordException;
import com.io.financio.domain.model.Session;
import com.io.financio.domain.model.request.LoginUserRequest;
import com.io.financio.domain.model.response.AuthResponse;
import com.io.financio.domain.service.criptography.RsaEncryptService;
import com.io.financio.domain.service.hashing.PasswordDigest;
import com.io.financio.domain.service.session.CreateSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticateUseCase {

    private final AuthenticateDataProvider dataProvider;
    private final PasswordDigest passwordDigest;
    private final CreateSessionService sessionService;
    private final RsaEncryptService encryptService;

    public AuthenticateUseCase(AuthenticateDataProvider dataProvider, PasswordDigest passwordDigest, CreateSessionService sessionService, RsaEncryptService encryptService) {
        this.dataProvider = dataProvider;
        this.passwordDigest = passwordDigest;
        this.sessionService = sessionService;
        this.encryptService = encryptService;
    }

    public AuthResponse execute(LoginUserRequest userRequest) {
        log.info("m=execute, msg='authenticating {}'", userRequest.getUsername());

        var username = userRequest.getUsername();
        var encryptedPassword = digestPassword(userRequest);

        var user = dataProvider.execute(username, encryptedPassword);
        var session = sessionService.execute(user);

        return buildToken(session);
    }

    private AuthResponse buildToken(Session session) {
        var token = encryptService.execute(session.getId());

        return new AuthResponse(token);
    }

    private String digestPassword(LoginUserRequest userRequest) {
        try {
            return passwordDigest.execute(userRequest.getPassword());
        } catch (Exception e) {
            throw new DigestPasswordException(e.getMessage());
        }
    }
}
