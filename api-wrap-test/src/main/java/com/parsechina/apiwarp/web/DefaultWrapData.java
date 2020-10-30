package com.parsechina.apiwarp.web;

import com.seelyn.apiwrap.WrapData;
import com.seelyn.apiwrap.annotation.SignIgnore;

public class DefaultWrapData extends WrapData {

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
