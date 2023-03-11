package me.euris.mall.controller;

import me.euris.mall.model.domain.SysUser;
import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.model.vo.SysUserVO;
import me.euris.mall.service.intf.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 02:52:00
 */

@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * @Name signin
     * @Description 系统用户登录
     * @Author Euris Lee
     * @param: username 用户名称
     * @param: password 用户密码
     * @CreateTime 3/11/23 6:47 AM
     * @return: me.euris.mall.model.vo.ResponseVO<me.euris.mall.model.domain.SysUser>
     * @Throws
     */
    @PostMapping("/signin")
    public ResponseVO<SysUser> signin(String username, String password) {
        return ResponseVO.success();
    }

    /**
     * @Name createUser
     * @Description 系统用户创建
     * @Author Euris Lee
     * @param: vo 系统用户信息
     * @CreateTime 3/11/23 6:47 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @PostMapping("/sys/user/create")
    public ResponseVO<String> createUser(SysUserVO vo) {
        return sysUserService.create(vo);
    }

    /**
     * @Name updateUser
     * @Description 更新系统用户信息
     * @Author Euris Lee
     * @param: vo 系统用户信息
     * @CreateTime 3/11/23 6:48 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @PostMapping("/sys/user/update")
    public ResponseVO<String> updateUser(SysUserVO vo) {
        return sysUserService.update(vo);
    }

    /**
     * @Name updateUser
     * @Description 删除系统用户
     * @Author Euris Lee
     * @param: id 系统用户的id
     * @CreateTime 3/11/23 6:48 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @GetMapping("/sys/user/delete")
    public ResponseVO<String> updateUser(Long id) {
        return sysUserService.delete(id);
    }
}
