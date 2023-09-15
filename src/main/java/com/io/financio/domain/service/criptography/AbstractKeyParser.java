package com.io.financio.domain.service.criptography;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public abstract class AbstractKeyParser {

    public PublicKey getPublicKey(String key, String algorithm) throws InvalidKeySpecException, NoSuchAlgorithmException {
        var bytes = DatatypeConverter.parseBase64Binary(key);
        var keyFactory = KeyFactory.getInstance(algorithm);
        var spec = new X509EncodedKeySpec(bytes);
        return keyFactory.generatePublic(spec);
    }
}
