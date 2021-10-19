package com.cpucode.microapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 小程序网关
 * @author : cpucode
 * @date : 2021/10/19 15:24
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties
@SpringBootApplication
public class MicroappGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(MicroappGatewayApplication.class);
    }
}
