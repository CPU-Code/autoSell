package com.cpucode.annotations;

import java.lang.annotation.*;

/**
 * 协议处理类型注解
 *
 * @author : cpucode
 * @date : 2021/10/14 20:23
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ProcessType {
    String value();
}
