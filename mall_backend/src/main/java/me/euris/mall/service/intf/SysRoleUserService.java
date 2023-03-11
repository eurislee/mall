package me.euris.mall.service.intf;

import me.euris.mall.model.domain.SysUser;
import me.euris.mall.model.vo.ResponseVO;

import java.util.List;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 06:33:00
 */
public interface SysRoleUserService {
    List<SysUser> getListByRoleId(Long id);
}
