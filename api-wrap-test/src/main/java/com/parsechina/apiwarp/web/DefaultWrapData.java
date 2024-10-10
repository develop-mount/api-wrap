package com.parsechina.apiwarp.web;

import icu.develop.api.wrap.WrapRequest;
import icu.develop.api.wrap.annotation.SignIgnore;

public class DefaultWrapData extends WrapRequest {

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
