package com.io.financio.dataprovider.model;

import com.io.financio.domain.model.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
@Document("session_document")
@Builder
public class SessionDocument {

    @MongoId
    private String id;
    private String username;
    private Set<String> scopes;
    @Setter
    private LocalDateTime expirationDate;

    public Session toDomain() {
        return Session.builder()
                .id(this.id)
                .username(this.username)
                .scopes(this.scopes)
                .build();
    }

    public SessionDocument updateExpirationDate() {
        setExpirationDate(LocalDateTime.now());
        return this;
    }
}
