package com.io.financio.domain.service.criptography;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.GeneralSecurityException;
import java.util.Base64;

@Service
public class RsaEncryptService extends AbstractKeyParser {

    private final String rsaPublicKey;
    private final String algorithm;
    private final String cipherInstance;

    public RsaEncryptService(@Value("${crypt.rsa.rsaPublicKey}") String rsaPublicKey,
                             @Value("${crypt.rsa.algorithm}") String algorithm,
                             @Value("${crypt.rsa.cipherInstance}") String cipherInstance) {
        this.rsaPublicKey = rsaPublicKey;
        this.algorithm = algorithm;
        this.cipherInstance = cipherInstance;
    }

    public String execute(String data) {

        try {
            var cipher = Cipher.getInstance(cipherInstance);
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(rsaPublicKey, algorithm));

            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (GeneralSecurityException e) {
            //TODO criar exception de negocio
            throw new RuntimeException(e);
        }
    }
}
