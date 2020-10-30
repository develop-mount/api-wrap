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

```java
import com.seelyn.apiwrap.WrapData;
import com.seelyn.apiwrap.annotation.SignIgnore;

public class DefaultWrapData extends WrapData {

    // SignIgnore 注解标识标识忽略属性用于签名
    @SignIgnore
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

```

- 存储appKey和appSecret用于请求验证
```
@Autoware
private WrapStore wrapStore;

// 存储密钥
wrapStore.putSecret(appKey, appSecret);

```

- 配置文件
```
#===========api wrap===========
# app密钥，若用户没有自定义，则使用此
api.wrap.secret=testjjhdsa
# 单位秒，请求时间和服务器时间不能超过300秒
api.wrap.legal-time=300
#===========api wrap redis=============
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.database=0
```

## 客户端工具
- 客户端辅助签名工具

### 引入工具包
#### gradle
```
compile 'com.seelyn:api-wrap-client:{version}'
```
#### maven
```
<dependency>
    <groupId>com.seelyn</groupId>
    <artifactId>api-wrap-client</artifactId>
    <version>{version}</version>
</dependency>
```
### 使用说明
```
WrapClient wrapClient = WrapClient.create(appKey, appSecret);
WrapRequest<WrapData> request = wrapClient.wrap(Object)
```
