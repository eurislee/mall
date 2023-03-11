package me.euris.mall.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.euris.mall.dao.SysRoleAclDao;
import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.service.intf.SysRoleAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 06:25:00
 */

@Service
@Slf4j
public class SysRoleAclServiceImpl implements SysRoleAclService {

    @Autowired
    private SysRoleAclDao sysRoleAclDao;

    @Override
    public ResponseVO<String> create() {

        return null;
    }

    @Override
    public ResponseVO<String> update() {
        return null;
    }

    @Override
    public ResponseVO<String> delete() {
        return null;
    }
}
