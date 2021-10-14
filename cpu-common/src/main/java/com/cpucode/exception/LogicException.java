package com.cpucode.exception;

/**
 * 逻辑异常
 * @author : cpucode
 * @date : 2021/10/14 20:24
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public class LogicException extends RuntimeException{
    public LogicException(String errorMsg){
        super(errorMsg);
    }

}
