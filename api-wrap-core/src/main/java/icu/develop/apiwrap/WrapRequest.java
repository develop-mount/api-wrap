package icu.develop.apiwrap;

/**
 * API包裹请求
 *
 * @author linfeng
 * @param <T> 继承WrapData {@link WrapData}
 */
public class WrapRequest<T extends WrapData> {

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
    /**
     * api调用数据
     */
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
