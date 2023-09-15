package com.io.financio.domain.usecase.login;

import com.io.financio.domain.dataprovider.login.LoginDataProvider;
import com.io.financio.domain.model.request.LoginUserRequest;
import com.io.financio.domain.service.hashing.PasswordDigest;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class LoginUseCase {

    private final LoginDataProvider dataProvider;
    private final PasswordDigest passwordDigest;

    public LoginUseCase(LoginDataProvider dataProvider, PasswordDigest passwordDigest) {
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
