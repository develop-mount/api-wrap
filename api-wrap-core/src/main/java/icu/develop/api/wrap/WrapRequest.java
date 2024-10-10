package icu.develop.api.wrap;

/**
 * API包裹请求
 *
 * @author linfeng
 */
public class WrapRequest {

    /**
     * 应用Key
     */
    private String appKey;
    /**
     * 签名信息
     */
    private String signature;
    /**
     * 时间戳
     */
    private long timestamp;
    /**
     * 随机数
     */
    private int nonce;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

}
