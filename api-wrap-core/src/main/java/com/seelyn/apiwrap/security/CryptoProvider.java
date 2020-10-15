package com.seelyn.apiwrap.security;

import javax.crypto.Mac;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/**
 * 密码提供接口
 *
 * @author linfeng
 */
public interface CryptoProvider {
    /**
     * Return an instance of a Mac digest for the provided algorithm name.
     *
     * @param algorithmName the name of the algorithm.
     * @return a Mac instance
     * @throws NoSuchAlgorithmException thrown when the requested algorithm cannot be satisfied by this crypto provider.
     */
    Mac getMacInstance(String algorithmName) throws NoSuchAlgorithmException;

    /**
     * Return an instance of a Signature digest for the provided algorithm name.
     *
     * @param algorithmName the name of the algorithm.
     * @return a Signature instance
     * @throws NoSuchAlgorithmException thrown when the requested algorithm cannot be satisfied by this crypto provider.
     */
    Signature getSignatureInstance(String algorithmName) throws NoSuchAlgorithmException;
}
