package com.io.financio.dataprovider.session;

import com.io.financio.config.security.dataprovider.ValidateSessionDataProvider;
import com.io.financio.dataprovider.model.SessionDocument;
import com.io.financio.dataprovider.repository.SessionRepository;
import com.io.financio.domain.model.Session;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidateSessionDataProviderImpl implements ValidateSessionDataProvider {

    private final SessionRepository repository;

    public ValidateSessionDataProviderImpl(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String sessionId) throws Exception {
        var result = repository.findById(sessionId);

        if(result.isEmpty()) {
            //TODO mapear exception
            throw new Exception();
        }

        repository.save(result.get().updateExpirationDate());
    }
}
