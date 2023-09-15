package com.io.financio.domain.service.hashing;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordDigest {

    public  final int HEXADECIMAL = 16;
    public  final int POSITIVE_NUMBER = 1;

    public String execute(String password) throws NoSuchAlgorithmException {
        var digestedMessage = MessageDigest
                .getInstance("SHA-256")
                .digest(password.getBytes());

        return new BigInteger(POSITIVE_NUMBER, digestedMessage).toString(HEXADECIMAL);
    }
}
