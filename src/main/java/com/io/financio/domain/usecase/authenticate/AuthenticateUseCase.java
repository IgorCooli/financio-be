package com.io.financio.domain.usecase.authenticate;

import com.io.financio.domain.dataprovider.authenticate.AuthenticateDataProvider;
import com.io.financio.domain.model.request.LoginUserRequest;
import com.io.financio.domain.service.hashing.PasswordDigest;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AuthenticateUseCase {

    private final AuthenticateDataProvider dataProvider;
    private final PasswordDigest passwordDigest;

    public AuthenticateUseCase(AuthenticateDataProvider dataProvider, PasswordDigest passwordDigest) {
        this.dataProvider = dataProvider;
        this.passwordDigest = passwordDigest;
    }

    public String execute(LoginUserRequest userRequest) {
        var username = userRequest.getUsername();
        var encryptedPassword = digestPassword(userRequest);

        //TODO criar token baseado no usuário encontrado e retornar
        return dataProvider.execute(username, encryptedPassword).toString();
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
