package com.io.financio.domain.service.criptography;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class RsaEncryptService {

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
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());

            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (GeneralSecurityException e) {
            //TODO criar exception de negocio
            throw new RuntimeException(e);
        }
    }

    private Key getPublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        var bytes = DatatypeConverter.parseBase64Binary(rsaPublicKey);
        var keyFactory = KeyFactory.getInstance(algorithm);

        var spec = new X509EncodedKeySpec(bytes);
        return keyFactory.generatePublic(spec);
    }
}
