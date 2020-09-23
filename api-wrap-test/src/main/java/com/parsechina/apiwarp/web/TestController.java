package com.parsechina.apiwarp.web;

import com.seelyn.apiwrap.WrapRequest;
import com.seelyn.apiwrap.annotation.ApiWrap;
import com.seelyn.apiwrap.annotation.EnableApiWrap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @ApiWrap
    @PostMapping(value = "/web")
    public WrapRequest<DefaultWrapData> web(@RequestBody WrapRequest<DefaultWrapData> request) {
        return request;
    }

}
