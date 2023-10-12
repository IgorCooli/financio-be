package com.io.financio.domain.service.session;

import com.io.financio.domain.dataprovider.session.CreateSessionDataProvider;
import com.io.financio.domain.model.Session;
import com.io.financio.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class CreateSessionService {

    private final CreateSessionDataProvider dataProvider;

    public CreateSessionService(CreateSessionDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Session execute(User user) {
        return dataProvider.execute(user.getUsername(), user.getScopes());
    }
}
