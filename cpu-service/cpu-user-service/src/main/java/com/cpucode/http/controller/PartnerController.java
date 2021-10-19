package com.cpucode.http.controller;

import com.cpucode.entity.PartnerEntity;
import com.cpucode.feignService.VMService;
import com.cpucode.http.viewModel.PartnerReq;
import com.cpucode.http.viewModel.PartnerUpdatePwdReq;
import com.cpucode.service.PartnerService;
import com.cpucode.utils.BCrypt;
import com.cpucode.viewmodel.Pager;
import com.cpucode.viewmodel.PartnerViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author : cpucode
 * @date : 2021/10/19 14:05
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RestController
@RequestMapping("/partner")
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;
    private final VMService vmService;

    /**
     * 新增合作商
     * @param partnerReq
     * @return
     */
    @PostMapping
    public Boolean add(@RequestBody PartnerReq partnerReq){
        PartnerEntity partnerEntity = new PartnerEntity();
        BeanUtils.copyProperties(partnerReq, partnerEntity);

        partnerEntity.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt()));

        return partnerService.save(partnerEntity);
    }

    /**
     * 获取合作商名称
     * @param id 主键
     * @return
     */
    @GetMapping("/name")
    public String getPartnerName(@PathVariable Integer id){
        return partnerService.getById(id).getName();
    }

    /**
     * 更新合作商
     * @param id 主键
     * @param partnerReq 更新内容
     * @return
     */
    @PutMapping("/{id}")
    public Boolean update(@PathVariable Integer id,
                          @RequestBody PartnerReq partnerReq){
        return partnerService.modify(id, partnerReq);
    }

    /**
     * 删除合作商
     * @param id 主键
     * @return
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        return partnerService.delete(id);
    }

    /**
     * 根据合作商Id获取详情
     * @param id 主键
     * @return
     */
    @GetMapping("/{id}")
    public PartnerViewModel getById(@PathVariable Integer id){
        PartnerEntity partnerEntity = partnerService.getById(id);
        if(partnerEntity == null) {
            return null;
        }

        PartnerViewModel vo = new PartnerViewModel();
        BeanUtils.copyProperties(partnerEntity, vo);

        //获取机器总数
        vo.setVmCount(vmService.getVmCountByOwnerId(id));

        return vo;
    }

    /**
     * 查询合作商
     * @param pageIndex
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/search")
    public Pager<PartnerEntity> search(
            @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize",required = false,defaultValue = "1") Long pageSize,
            @RequestParam(value = "name",required = false, defaultValue = "") String name){
        return partnerService.search(pageIndex, pageSize, name);
    }


    /**
     * 重置密码
     * @param id
     */
    @PutMapping("/resetPwd/{id}")
    public void resetPwd(@PathVariable Integer id){
        partnerService.resetPwd(id);
    }

    /**
     * 更新密码
     * @param req 根据id
     * @return
     */
    @PutMapping("/updatePwd/{id}")
    public Boolean modifyPwd(@PathVariable Integer id,
                             @RequestBody PartnerUpdatePwdReq req){
        return partnerService.updatePwd(id, req);
    }

}
