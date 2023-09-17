package com.io.financio.config.security.dataprovider;

import com.io.financio.domain.model.Session;

public interface ValidateSessionDataProvider {

    void execute(String sessionId) throws Exception;

}
