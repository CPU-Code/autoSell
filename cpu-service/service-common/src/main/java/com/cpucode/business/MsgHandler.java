package com.cpucode.business;

import java.io.IOException;

/**
 * 消息处理接口
 *
 * @author : cpucode
 * @date : 2021/10/19 15:56
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface MsgHandler {
    void process(String jsonMsg) throws IOException;
}
