package com.seelyn.apiwrap;

import com.seelyn.apiwrap.exception.InvalidWrapSignatureException;
import com.seelyn.apiwrap.exception.WrapSigningException;
import com.seelyn.apiwrap.exception.WrapVerifierException;
import com.seelyn.apiwrap.security.CryptoProvider;
import com.seelyn.apiwrap.security.DefaultCryptoProvider;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * HMAC算法，签名验证
 *
 * @author linfeng-eqxiu
 */
public class HMACWrap implements Wrap {

    /**
     * 算法
     */
    private final Algorithm algorithm;
    /**
     * 密码提供者
     */
    private final CryptoProvider cryptoProvider;
    /**
     * 密钥
     */
    private final byte[] secret;

    /**
     * MHAC构造函数
     *
     * @param algorithm      算法
     * @param secret         密钥
     * @param cryptoProvider 密码提供者
     */
    private HMACWrap(Algorithm algorithm, byte[] secret, CryptoProvider cryptoProvider) {
        Objects.requireNonNull(algorithm);
        Objects.requireNonNull(cryptoProvider);
        Objects.requireNonNull(secret);

        this.algorithm = algorithm;
        this.cryptoProvider = cryptoProvider;
        this.secret = secret;
    }

    /**
     * MHAC构造函数
     *
     * @param algorithm      算法
     * @param secret         密钥
     * @param cryptoProvider 密码提供者
     */
    private HMACWrap(Algorithm algorithm, String secret, CryptoProvider cryptoProvider) {
        Objects.requireNonNull(algorithm);
        Objects.requireNonNull(cryptoProvider);
        Objects.requireNonNull(secret);

        this.algorithm = algorithm;
        this.cryptoProvider = cryptoProvider;
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    @Override
    public byte[] sign(String payload) {
        Objects.requireNonNull(payload);

        try {
            Mac mac = cryptoProvider.getMacInstance(algorithm.getName());
            mac.init(new SecretKeySpec(secret, algorithm.getName()));
            return mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new WrapSigningException("An unexpected exception occurred when attempting to sign the JWT", e);
        }
    }

    @Override
    public void verify(byte[] payload, byte[] signature) {
        Objects.requireNonNull(algorithm);
        Objects.requireNonNull(payload);
        Objects.requireNonNull(signature);

        try {
            Mac mac = cryptoProvider.getMacInstance(algorithm.getName());
            mac.init(new SecretKeySpec(secret, algorithm.getName()));
            byte[] actualSignature = mac.doFinal(payload);

            if (!MessageDigest.isEqual(signature, actualSignature)) {
                throw new InvalidWrapSignatureException();
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new WrapVerifierException("An unexpected exception occurred when attempting to verify the wrap", e);
        }
    }

    /**
     * 用SHA-256哈希算法构建HMAC Wrap
     *
     * @param secret 构建HMAC哈希的密钥
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA256Wrap(byte[] secret) {
        return newSHA256Wrap(secret, new DefaultCryptoProvider());
    }

    /**
     * 用SHA-256哈希算法构建HMAC Wrap
     *
     * @param secret 构建HMAC哈希的密钥
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA256Wrap(String secret) {
        return newSHA256Wrap(secret, new DefaultCryptoProvider());
    }

    /**
     * 用SHA-256哈希算法构建HMAC Wrap
     *
     * @param secret         构建HMAC哈希的密钥
     * @param cryptoProvider 用于获取MAC摘要算法的加密提供程序
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA256Wrap(String secret, CryptoProvider cryptoProvider) {
        return new HMACWrap(Algorithm.HS256, secret, cryptoProvider);
    }

    /**
     * 用SHA-256哈希算法构建HMAC Wrap
     *
     * @param secret         构建HMAC哈希的密钥
     * @param cryptoProvider 用于获取MAC摘要算法的加密提供程序
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA256Wrap(byte[] secret, CryptoProvider cryptoProvider) {
        return new HMACWrap(Algorithm.HS256, secret, cryptoProvider);
    }

    /**
     * 用SHA-384哈希算法构建HMAC Wrap
     *
     * @param secret 构建HMAC哈希的密钥
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA384Wrap(byte[] secret) {
        return newSHA384Wrap(secret, new DefaultCryptoProvider());
    }

    /**
     * 用SHA-384哈希算法构建HMAC Wrap
     *
     * @param secret 构建HMAC哈希的密钥
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA384Wrap(String secret) {
        return newSHA384Wrap(secret, new DefaultCryptoProvider());
    }

    /**
     * 用SHA-384哈希算法构建HMAC Wrap
     *
     * @param secret         构建HMAC哈希的密钥
     * @param cryptoProvider 用于获取MAC摘要算法的加密提供程序
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA384Wrap(byte[] secret, CryptoProvider cryptoProvider) {
        return new HMACWrap(Algorithm.HS384, secret, cryptoProvider);
    }

    /**
     * 用SHA-384哈希算法构建HMAC Wrap
     *
     * @param secret         构建HMAC哈希的密钥
     * @param cryptoProvider 用于获取MAC摘要算法的加密提供程序
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA384Wrap(String secret, CryptoProvider cryptoProvider) {
        return new HMACWrap(Algorithm.HS384, secret, cryptoProvider);
    }

    /**
     * 用SHA-512哈希算法构建HMAC Wrap
     *
     * @param secret 构建HMAC哈希的密钥
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA512Wrap(byte[] secret) {
        return newSHA512Wrap(secret, new DefaultCryptoProvider());
    }

    /**
     * 用SHA-512哈希算法构建HMAC Wrap
     *
     * @param secret 构建HMAC哈希的密钥
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA512Wrap(String secret) {
        return newSHA512Wrap(secret, new DefaultCryptoProvider());
    }


    /**
     * 用SHA-512哈希算法构建HMAC Wrap
     *
     * @param secret         构建HMAC哈希的密钥
     * @param cryptoProvider 用于获取MAC摘要算法的加密提供程序
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA512Wrap(String secret, CryptoProvider cryptoProvider) {
        return new HMACWrap(Algorithm.HS512, secret, cryptoProvider);
    }

    /**
     * 用SHA-512哈希算法构建HMAC Wrap
     *
     * @param secret         构建HMAC哈希的密钥
     * @param cryptoProvider 用于获取MAC摘要算法的加密提供程序
     * @return a new HMAC wrap.
     */
    public static HMACWrap newSHA512Wrap(byte[] secret, CryptoProvider cryptoProvider) {
        return new HMACWrap(Algorithm.HS512, secret, cryptoProvider);
    }

}
