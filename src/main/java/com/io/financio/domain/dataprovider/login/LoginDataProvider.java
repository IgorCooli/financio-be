package com.io.financio.domain.dataprovider.login;

import com.io.financio.domain.model.User;
import com.io.financio.domain.model.request.LoginUserRequest;

public interface LoginDataProvider {

    User execute(LoginUserRequest request);

}
