package com.cpucode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : cpucode
 * @date : 2021/10/19 14:50
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run( GatewayApplication.class, args);
    }
}
