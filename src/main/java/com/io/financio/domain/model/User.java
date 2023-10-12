package com.io.financio.domain.model;

import com.google.common.base.MoreObjects;
import com.io.financio.domain.model.enums.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
public class User {

    private final String id;
    private final String username;
    private final String password;
    private final String name;
    private final String lastName;
    private final String phoneNumber;
    private final double monthlyRevenue;
    private final Set<String> scopes;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final UserStatus status;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("username", username)
                .add("password", password)
                .add("scopes", scopes)
                .add("createdAt", createdAt)
                .add("updatedAt", updatedAt)
                .add("status", status)
                .toString();
    }
}
