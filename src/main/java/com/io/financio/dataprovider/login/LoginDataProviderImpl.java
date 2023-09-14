package com.io.financio.dataprovider.login;

import com.io.financio.dataprovider.model.UserDocument;
import com.io.financio.dataprovider.repository.UserRepository;
import com.io.financio.domain.dataprovider.login.LoginDataProvider;
import com.io.financio.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class LoginDataProviderImpl implements LoginDataProvider {

    private final UserRepository repository;

    public LoginDataProviderImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(String username, String password) {
        var optDocument = repository.findByUsernameAndPassword(username, password);

        //TODO ajustar o retorno abaixo
        return buildUser(optDocument.get());
    }

    private User buildUser(UserDocument document) {
        return User.builder()
                .id(document.getId())
                .username(document.getUsername())
                .password(document.getPassword())
                .scopes(document.getScopes())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .status(document.getStatus())
                .build();
    }
}
