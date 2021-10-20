package com.cpucode.vms.http.controller;

import com.cpucode.vms.entity.BusinessTypeEntity;
import com.cpucode.vms.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/10/20 11:17
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RestController("/businessType")
public class BusinessTypeController {
    @Autowired
    private BusinessTypeService businessTypeService;

    /**
     * 获取所有商圈
     * @return
     */
    @GetMapping()
    public List<BusinessTypeEntity> getAll(){
        return businessTypeService.list();
    }

}
