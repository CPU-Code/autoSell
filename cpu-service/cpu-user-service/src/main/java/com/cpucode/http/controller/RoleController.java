package com.cpucode.http.controller;

import com.cpucode.entity.RoleEntity;
import com.cpucode.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色
 * @author : cpucode
 * @date : 2021/10/19 14:01
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    /**
     * 查询所有角色
     * @return 列表
     */
    @GetMapping
    public List<RoleEntity> findList(){
        return roleService.list();
    }
}
