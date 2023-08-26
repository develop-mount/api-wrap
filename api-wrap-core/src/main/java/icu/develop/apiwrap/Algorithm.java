package icu.develop.apiwrap;

/**
 * 算法
 *
 * @author linfeng-eqxiu
 */
public enum Algorithm {
    /**
     * ECDSA using P-256 and SHA-256
     * OID: 1.2.840.10045.3.1.7
     * - prime256v1 / secp256r1
     */
    ES256("SHA256withECDSA"),

    /**
     * ECDSA using P-384 and SHA-384
     * OID: 1.3.132.0.34
     * - secp384r1 / secp384r1
     */
    ES384("SHA384withECDSA"),

    /**
     * ECDSA using P-521 and SHA-512
     * OID: 1.3.132.0.35
     * - prime521v1 / secp521r1
     */
    ES512("SHA512withECDSA"),

    /**
     * HMAC using SHA-256
     */
    HS256("HmacSHA256"),

    /**
     * HMAC using SHA-384
     */
    HS384("HmacSHA384"),

    /**
     * HMAC using SHA-512
     */
    HS512("HmacSHA512"),

    /**
     * RSASSA-PSS using SHA-256 and MGF1 with SHA-256
     * - SHA256withRSAandMGF1
     */
    PS256("SHA-256"),

    /**
     * RSASSA-PSS using SHA-384 and MGF1 with SHA-384
     * - SHA384withRSAandMGF1
     */
    PS384("SHA-384"),

    /**
     * RSASSA-PSS using SHA-512 and MGF1 with SHA-512
     * - SHA512withRSAandMGF1
     */
    PS512("SHA-512"),

    /**
     * RSASSA-PKCS1-v1_5 using SHA-256
     */
    RS256("SHA256withRSA"),

    /**
     * RSASSA-PKCS1-v1_5 using SHA-384
     */
    RS384("SHA384withRSA"),

    /**
     * RSASSA-PKCS1-v1_5 using SHA-512
     */
    RS512("SHA512withRSA"),

    /**
     * No digital signature or MAC performed.
     */
    NONE("None");

    private String algorithm;

    Algorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public static Algorithm fromName(String name) {
        for (Algorithm alg : Algorithm.values()) {
            if (alg.getName().equals(name)) {
                return alg;
            }
        }
        return null;
    }

    public String getName() {
        return algorithm;
    }

    public int getSaltLength() {
        switch (this) {
            case PS256:
                return 32;
            case PS384:
                return 48;
            case PS512:
                return 64;
            default:
                throw new IllegalStateException("An incompatible algorithm was provided, this method is only used for RSASSA-PSS algorithms.");
        }
    }
}
