package com.io.financio.dataprovider.registeruser;

import com.io.financio.dataprovider.model.UserDocument;
import com.io.financio.dataprovider.repository.UserRepository;
import com.io.financio.domain.dataprovider.registeruser.RegisterUserDataProvider;
import com.io.financio.domain.exception.UserAlreadyRegisteredException;
import com.io.financio.domain.model.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserDataProviderImpl implements RegisterUserDataProvider {

    private final UserRepository repository;

    public RegisterUserDataProviderImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(User user) {
        var document = buildDocument(user);

        try {
            repository.save(document);
        } catch (DuplicateKeyException ex) {
            //TODO logs
            throw new UserAlreadyRegisteredException(user.getUsername());
        }
    }

    private UserDocument buildDocument(User user) {
        return UserDocument.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .monthlyRevenue(user.getMonthlyRevenue())
                .scopes(user.getScopes())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .status(user.getStatus())
                .build();
    }
}
