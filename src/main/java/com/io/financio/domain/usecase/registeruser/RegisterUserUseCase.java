package com.io.financio.domain.usecase.registeruser;

import com.io.financio.domain.dataprovider.registeruser.RegisterUserDataProvider;
import com.io.financio.domain.model.User;
import com.io.financio.domain.model.enums.UserStatus;
import com.io.financio.domain.model.request.RegisterUserRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class RegisterUserUseCase {

    private final RegisterUserDataProvider dataProvider;

    public RegisterUserUseCase(RegisterUserDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void execute(RegisterUserRequest request) {
        var user = buildUser(request);

        dataProvider.execute(user);
    }

    private User buildUser(RegisterUserRequest request) {
        var now = LocalDateTime.now();
        return User.builder()
                .username(request.getUsername())
                //TODO encriptar senha antes de passar para o obj
                .password(request.getPassword())
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
}
