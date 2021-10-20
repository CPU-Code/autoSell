package com.cpucode.vms.busines.msgHandler;

import com.cpucode.annotations.ProcessType;
import com.cpucode.business.MsgHandler;
import com.cpucode.vms.service.VmCfgVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 处理货道请求
 *
 * @author : cpucode
 * @date : 2021/10/19 15:53
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Component
@ProcessType(value = "channelCfgReq")
public class ChannelCfgMsgHandler implements MsgHandler {
    @Autowired
    private VmCfgVersionService versionService;
    @Autowired
    private VendingMachineService vmService;
    @Autowired
    private MqttProducer mqttProducer;

    @Override
    public void process(String jsonMsg) throws IOException {

    }
}
