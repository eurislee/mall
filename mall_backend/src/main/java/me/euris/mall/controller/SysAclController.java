package me.euris.mall.controller;

import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.model.vo.SysAclVO;
import me.euris.mall.service.intf.SysAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 06:07:00
 */

@RestController
public class SysAclController {
    @Autowired
    private SysAclService sysAclService;

    /**
     * @Name createAcl
     * @Description 创建权限
     * @Author Euris Lee
     * @param: vo 权限信息
     * @CreateTime 3/11/23 6:50 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @PostMapping("/sys/acl/create")
    public ResponseVO<String> createAcl(SysAclVO vo) {
        return sysAclService.create(vo);
    }

    /**
     * @Name updateAcl
     * @Description 更新权限信息
     * @Author Euris Lee
     * @param: vo 权限信息
     * @CreateTime 3/11/23 6:50 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @PostMapping("/sys/acl/update")
    public ResponseVO<String> updateAcl(SysAclVO vo) {
        return sysAclService.update(vo);
    }
}
