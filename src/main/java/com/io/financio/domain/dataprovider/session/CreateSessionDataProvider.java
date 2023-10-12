package com.io.financio.domain.dataprovider.session;

import com.io.financio.domain.model.Session;

import java.util.Set;

public interface CreateSessionDataProvider {
    Session execute(String username, Set<String> scopes);
}
