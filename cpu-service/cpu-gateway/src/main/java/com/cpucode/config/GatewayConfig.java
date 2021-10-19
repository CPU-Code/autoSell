package com.cpucode.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 需要排除拦截的url配置
 *
 * @author : cpucode
 * @date : 2021/10/19 14:53
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Configuration
@ConfigurationProperties("skipauth")
public class GatewayConfig {
    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    private String[] urls;
}
