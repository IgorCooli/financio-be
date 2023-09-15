package com.io.financio.domain.service.criptography;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.GeneralSecurityException;
import java.util.Base64;

@Service
public class RsaDecryptService extends AbstractKeyParser {

    private final String rsaPrivateKey;
    private final String algorithm;
    private final String cipherInstance;

    public RsaDecryptService(@Value("${crypt.rsa.rsaPrivateKey}") String rsaPrivateKey,
                             @Value("${crypt.rsa.algorithm}") String algorithm,
                             @Value("${crypt.rsa.cipherInstance}") String cipherInstance) {
        this.rsaPrivateKey = rsaPrivateKey;
        this.algorithm = algorithm;
        this.cipherInstance = cipherInstance;
    }

    public String execute(String data) {

        try {
            var cipher = Cipher.getInstance(cipherInstance);
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(rsaPrivateKey, algorithm));

            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (GeneralSecurityException e) {
            //TODO criar exception de negocio
            throw new RuntimeException(e);
        }
    }
}
