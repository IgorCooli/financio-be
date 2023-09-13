package com.io.financio.domain.usecase.login;

import com.io.financio.domain.dataprovider.login.LoginDataProvider;
import com.io.financio.domain.model.request.LoginUserRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final LoginDataProvider dataProvider;

    public LoginUseCase(LoginDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public String execute(LoginUserRequest userRequest) {
        //TODO criar token baseado no usuário encontrado e retornar
        return dataProvider.execute(userRequest).toString();
    }
}
