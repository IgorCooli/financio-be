package com.io.financio.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUserRequest {
    private String username;
    private String password;
}
