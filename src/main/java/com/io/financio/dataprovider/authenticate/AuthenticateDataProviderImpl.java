package com.io.financio.dataprovider.authenticate;

import com.io.financio.dataprovider.model.UserDocument;
import com.io.financio.dataprovider.repository.UserRepository;
import com.io.financio.domain.dataprovider.authenticate.AuthenticateDataProvider;
import com.io.financio.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateDataProviderImpl implements AuthenticateDataProvider {

    private final UserRepository repository;

    public AuthenticateDataProviderImpl(UserRepository repository) {
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
