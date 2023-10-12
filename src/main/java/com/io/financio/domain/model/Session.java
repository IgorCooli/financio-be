package com.io.financio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Builder
public class Session {
    private String id;
    private String username;
    private Set<String> scopes;
}
