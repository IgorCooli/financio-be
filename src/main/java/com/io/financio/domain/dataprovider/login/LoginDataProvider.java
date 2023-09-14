package com.io.financio.domain.dataprovider.login;

import com.io.financio.domain.model.User;

public interface LoginDataProvider {

    User execute(String username, String password);

}
