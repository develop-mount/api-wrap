# API 安全签名验签

## 引入jar包
### gradle
```
compile 'com.seelyn:api-wrap-boot:{version}'
```
### maven
```
<dependency>
    <groupId>com.seelyn</groupId>
    <artifactId>api-wrap-boot</artifactId>
    <version>{version}</version>
</dependency>
```
## 使用示例

- 使用EnableApiWrap注解，开启API Wrap功能 
```java
@SpringBootApplication
@EnableApiWrap
public class WrapWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WrapWebApplication.class, args);
    }

}
```

- 在controller类方法或类上添加@ApiWrap注解, 注解也可以自定义处理类，只有使用了注解的类和方法才能接收签名验签请求
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

- 配置文件
```
#===========api wrap===========
api.wrap.enable=false
api.wrap.secret=testjjhdsa
api.wrap.legal-time=300
#===========api wrap redis=============
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.database=0
```
