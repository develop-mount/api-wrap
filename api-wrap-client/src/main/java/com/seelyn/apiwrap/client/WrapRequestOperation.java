package com.seelyn.apiwrap.client;

import com.seelyn.apiwrap.WrapData;
import com.seelyn.apiwrap.WrapRequest;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class WrapRequestOperation implements Operations {

    private final OkHttpClient httpClient;

    public WrapRequestOperation() {
        this.httpClient = new OkHttpClient();
    }

    @Override
    public <T extends WrapData> String postJson(String url, WrapRequest<T> request) {

        return null;
    }

    /**
     * 执行post提交
     *
     * @param json 传递的json内容
     * @return 返回的字符串
     * @throws IOException 异常
     */
    private String doPostExecute(String url, String json) throws IOException {

        MediaType jsonMediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonMediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            return Objects.isNull(response.body()) ? "" : response.body().string();
        }
    }
}
