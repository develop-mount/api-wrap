# API 安全签名验签

## 引入jar包
### gradle
```
compile 'com.eqxiu:eqxiu-api-wrap-boot:{version}'
```
### maven
```
<dependency>
    <groupId>com.eqxiu</groupId>
    <artifactId>eqxiu-api-wrap-boot</artifactId>
    <version>{version}</version>
</dependency>
```
## 使用示例

- @ApiWrap 是注解，可以使用在类和方法上, 注解也可以自定义处理类，只有使用了注解的类和方法才能接收签名验签请求
```
@ApiWrap
@PostMapping(value = "/web")
public WrapRequest<DefaultWrapData> web(@RequestBody WrapRequest<DefaultWrapData> request) {
    return request;
}
```

- 自定义API签名验签处理类
```
@ApiWrap(value=CustomWrapHandler.class)
CustomWrapHandler类需要实现WrapHandler接口
```

- WrapRequest<DefaultWrapData> 是统一的API验证请求类，其中泛型 DefaultWrapData类需要继承WrapData类

```java
public class WrapRequest<T extends WrapData> {

    private String appKey;
    private String signature;
    private long timestamp;
    private int nonce;
    private T data;
    //setter getter省略.... 
}
```

- 存储appKey和appSecret用于请求验证
```java
@Autoware
private WrapStore wrapStore;

// 存储密钥
wrapStore.putSecret(appKey, appSecret);

```
