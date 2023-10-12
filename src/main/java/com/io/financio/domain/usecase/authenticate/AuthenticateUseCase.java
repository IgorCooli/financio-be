package com.io.financio.domain.usecase.authenticate;

import com.io.financio.domain.dataprovider.authenticate.AuthenticateDataProvider;
import com.io.financio.domain.model.Session;
import com.io.financio.domain.model.request.LoginUserRequest;
import com.io.financio.domain.service.criptography.RsaEncryptService;
import com.io.financio.domain.service.hashing.PasswordDigest;
import com.io.financio.domain.service.session.CreateSessionService;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
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

    public String execute(LoginUserRequest userRequest) {
        var username = userRequest.getUsername();
        var encryptedPassword = digestPassword(userRequest);

        var user = dataProvider.execute(username, encryptedPassword);
        var session = sessionService.execute(user);

        return buildToken(session);
    }

    private String buildToken(Session session) {
            return encryptService.execute(session.getId());
    }

    private String digestPassword(LoginUserRequest userRequest) {
        try {
            return passwordDigest.execute(userRequest.getPassword());
        } catch (NoSuchAlgorithmException e) {
            //TODO criar exception de negocio
            throw new RuntimeException(e);
        }
    }
}
