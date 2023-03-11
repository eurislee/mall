package me.euris.mall.controller;

import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.model.vo.SysAclModuleVO;
import me.euris.mall.service.intf.SysAclModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 05:09:00
 */

@RestController
public class SysAclModuleController {
    @Autowired
    private SysAclModuleService sysAclModuleService;

    /**
     * @Name createAclModule
     * @Description 创建权限模块
     * @Author Euris Lee
     * @param: vo 权限模块信息
     * @CreateTime 3/11/23 6:48 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @PostMapping("/sys/acl/module/create")
    public ResponseVO<String> createAclModule(SysAclModuleVO vo) {
        return sysAclModuleService.create(vo);
    }

    /**
     * @Name updateAclModule
     * @Description 更新权限模块
     * @Author Euris Lee
     * @param: vo 权限模块信息
     * @CreateTime 3/11/23 6:49 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @PostMapping("/sys/acl/module/update")
    public ResponseVO<String> updateAclModule(SysAclModuleVO vo) {
        return sysAclModuleService.update(vo);
    }

    /**
     * @Name deleteAclModule
     * @Description 删除权限模块
     * @Author Euris Lee
     * @param: id 权限模块的id
     * @CreateTime 3/11/23 6:49 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @GetMapping("/sys/acl/module/delete")
    public ResponseVO<String> deleteAclModule(Long id) {
        return sysAclModuleService.delete(id);
    }
}
