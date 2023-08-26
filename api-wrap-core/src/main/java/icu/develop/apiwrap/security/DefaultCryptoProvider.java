package icu.develop.apiwrap.security;

import javax.crypto.Mac;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/**
 * 默认密码提供者
 *
 * @author linfeng
 */
public class DefaultCryptoProvider implements CryptoProvider {
    @Override
    public Mac getMacInstance(String name) throws NoSuchAlgorithmException {
        return Mac.getInstance(name);
    }

    @Override
    public Signature getSignatureInstance(String name) throws NoSuchAlgorithmException {
        return Signature.getInstance(name);
    }
}
