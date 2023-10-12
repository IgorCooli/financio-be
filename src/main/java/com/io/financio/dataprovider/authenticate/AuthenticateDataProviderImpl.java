package com.io.financio.dataprovider.authenticate;

import com.io.financio.dataprovider.model.UserDocument;
import com.io.financio.dataprovider.repository.UserRepository;
import com.io.financio.domain.dataprovider.authenticate.AuthenticateDataProvider;
import com.io.financio.domain.exception.InvalidUsernameOrPasswordException;
import com.io.financio.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticateDataProviderImpl implements AuthenticateDataProvider {

    private final UserRepository repository;

    public AuthenticateDataProviderImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(String username, String password) {
        log.info("m=execute, msg='searching user on database', username={}", username);
        var optDocument = repository.findByUsernameAndPassword(username, password);

        return optDocument
                .map(this::buildUser)
                .orElseThrow(() -> new InvalidUsernameOrPasswordException("Invalid username or password"));
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
