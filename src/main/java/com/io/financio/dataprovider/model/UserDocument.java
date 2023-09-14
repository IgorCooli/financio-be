package com.io.financio.dataprovider.model;

import com.io.financio.domain.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
@Document("user_document")
@Builder
public class UserDocument {

    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String phoneNumber;
    private double monthlyRevenue;
    private Set<String> scopes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserStatus status;
}
