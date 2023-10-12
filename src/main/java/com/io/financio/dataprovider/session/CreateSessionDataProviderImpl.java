package com.io.financio.dataprovider.session;

import com.io.financio.dataprovider.model.SessionDocument;
import com.io.financio.dataprovider.repository.SessionRepository;
import com.io.financio.domain.dataprovider.session.CreateSessionDataProvider;
import com.io.financio.domain.model.Session;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class CreateSessionDataProviderImpl implements CreateSessionDataProvider {

    private final SessionRepository repository;

    public CreateSessionDataProviderImpl(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Session execute(String username, Set<String> scopes) {
        try {
            return  repository.save(buildDocument(username, scopes))
                    .toDomain();
        } catch (DuplicateKeyException ex) {
            //TODO logs
            return getExistingSession(username);
        }
    }

    private Session getExistingSession(String username) {
        return repository.findByUsername(username)
                .toDomain();
    }

    private SessionDocument buildDocument(String username, Set<String> scopes) {
        return SessionDocument.builder()
                .username(username)
                .scopes(scopes)
                .expirationDate(LocalDateTime.now())
                .build();
    }
}
