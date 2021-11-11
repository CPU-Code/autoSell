package com.cpucode.vms.http.controller;

import com.cpucode.vms.entity.VmPolicyEntity;
import com.cpucode.vms.service.VmPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 机器和策略关联
 *
 * @author : cpucode
 * @date : 2021/11/11 14:10
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Deprecated
@RestController
@RequestMapping("/vmPolicy")
public class VmPolicyController {

    @Autowired
    private VmPolicyService vmPolicyService;

    /**
     * 根据id查询
     * @param id
     * @return 实体
     */
    @GetMapping("/{id}")
    public VmPolicyEntity findById(@PathVariable Long id){
        return vmPolicyService.getById(id);
    }


    /**
     * 新增
     * @param vmPolicy
     * @return 是否成功
     */
    @PostMapping
    public boolean add(@RequestBody VmPolicyEntity vmPolicy){
        return vmPolicyService.save(vmPolicy);
    }


    /**
     * 修改
     * @param id 主键 , note:里面应该有
     * @param vmPolicy 机器和策略关联
     * @return 是否成功
     */
    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id,
                          @RequestBody VmPolicyEntity vmPolicy){
        vmPolicy.setId(id);

        return vmPolicyService.updateById(vmPolicy);
    }

}
