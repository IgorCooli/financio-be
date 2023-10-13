package com.io.financio.domain.usecase.registeruser;

import com.io.financio.domain.dataprovider.registeruser.RegisterUserDataProvider;
import com.io.financio.domain.exception.PasswordHashingException;
import com.io.financio.domain.model.User;
import com.io.financio.domain.model.enums.UserStatus;
import com.io.financio.domain.model.request.RegisterUserRequest;
import com.io.financio.domain.service.hashing.PasswordDigest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
public class RegisterUserUseCase {

    private final RegisterUserDataProvider dataProvider;
    private final PasswordDigest passwordDigest;

    public RegisterUserUseCase(RegisterUserDataProvider dataProvider, PasswordDigest passwordDigest) {
        this.dataProvider = dataProvider;
        this.passwordDigest = passwordDigest;
    }

    public void execute(RegisterUserRequest request) {
        log.info("m=execute, msg='registering new user', username={}", request.getUsername());
        var user = buildUser(request);

        dataProvider.execute(user);
    }

    private User buildUser(RegisterUserRequest request) {
        var now = LocalDateTime.now();
        var encryptedPassword = digestPassword(request);

        return User.builder()
                .username(request.getUsername())
                .password(encryptedPassword)
                .name(request.getName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .monthlyRevenue(request.getMonthlyRevenue())
                //TODO chamar serviço que definirá escopos
                .scopes(Set.of("generic.scope"))
                .createdAt(now)
                .updatedAt(now)
                .status(UserStatus.ACTIVE)
                .build();
    }

    private String digestPassword(RegisterUserRequest request) {
        try {
            return passwordDigest.execute(request.getPassword());
        } catch (Exception e) {
            throw new PasswordHashingException(e.getMessage());
        }
    }
}
