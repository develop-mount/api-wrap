package icu.develop.api.wrap;

public class SecretKey {
    private final String privateKey;
    private final String publicKey;

    public SecretKey(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
