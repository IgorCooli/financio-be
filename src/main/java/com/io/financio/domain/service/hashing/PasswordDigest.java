package com.io.financio.domain.service.hashing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordDigest {

    private  final int HEXADECIMAL = 16;
    private  final int POSITIVE_NUMBER = 1;
    private final String digestInstance;

    public PasswordDigest(@Value("${crypt.sha256.digestInstance}") String digestInstance) {
        this.digestInstance = digestInstance;
    }

    public String execute(String password) throws NoSuchAlgorithmException {
        var digestedMessage = MessageDigest
                .getInstance(digestInstance)
                .digest(password.getBytes());

        return new BigInteger(POSITIVE_NUMBER, digestedMessage).toString(HEXADECIMAL);
    }
}
