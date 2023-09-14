package com.io.financio.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserRequest {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String phoneNumber;
    private double monthlyRevenue;
}
