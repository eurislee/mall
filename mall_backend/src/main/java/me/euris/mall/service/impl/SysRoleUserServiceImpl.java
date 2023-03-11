package me.euris.mall.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.euris.mall.dao.SysRoleUserDao;
import me.euris.mall.dao.SysUserDao;
import me.euris.mall.model.domain.SysUser;
import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.service.intf.SysRoleUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 06:35:00
 */

@Service
@Slf4j
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Autowired
    private SysRoleUserDao sysRoleUserDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<SysUser> getListByRoleId(Long id) {
        List<Long> userIdList = sysRoleUserDao.selectUserIdListByRoleId(id);
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }

        return sysUserDao.selectByIdList(userIdList);
    }
}
