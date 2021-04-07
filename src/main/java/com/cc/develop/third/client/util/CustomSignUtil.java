package com.cc.develop.third.client.util;

import org.jolokia.util.Base64Util;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * com.cc.develop.third.client.util
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-03-22 16:27:51
 */
public class CustomSignUtil {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    public static String sign(byte[] data, String privateKey) throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, SignatureException {
        byte[] keyBytes = Base64Util.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64Util.encode(signature.sign());
    }
}
