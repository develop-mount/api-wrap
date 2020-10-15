package com.seelyn.apiwrap.client;

import com.seelyn.apiwrap.WrapData;
import com.seelyn.apiwrap.WrapRequest;

public interface Operations {

    <T extends WrapData> String postJson(String url, WrapRequest<T> request);
}
