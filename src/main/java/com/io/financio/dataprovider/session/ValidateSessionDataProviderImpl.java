package com.io.financio.dataprovider.session;

import com.io.financio.config.security.dataprovider.ValidateSessionDataProvider;
import com.io.financio.dataprovider.repository.SessionRepository;
import com.io.financio.domain.exception.SessionNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ValidateSessionDataProviderImpl implements ValidateSessionDataProvider {

    private final SessionRepository repository;

    public ValidateSessionDataProviderImpl(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String sessionId) {
        var result = repository.findById(sessionId);

        if(result.isEmpty()) {
            throw new SessionNotFoundException("session not found");
        }

        repository.save(result.get().updateExpirationDate());
    }
}
