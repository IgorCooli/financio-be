package com.io.financio.domain.service.criptography;

import com.io.financio.domain.exception.InvalidTokenDataException;
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
import java.util.Base64;

@Service
public class RsaDecryptService {

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
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());

            return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes())));
        } catch (GeneralSecurityException e) {
            //TODO logs
            throw new InvalidTokenDataException(e.getMessage());
        }
    }

    public Key getPrivateKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        var bytes = DatatypeConverter.parseBase64Binary(rsaPrivateKey);
        var keyFactory = KeyFactory.getInstance(algorithm);

        var spec = new PKCS8EncodedKeySpec(bytes);
        return keyFactory.generatePrivate(spec);
    }
}
