package com.io.financio.domain.dataprovider.authenticate;

import com.io.financio.domain.model.User;

public interface AuthenticateDataProvider {

    User execute(String username, String password);

}
