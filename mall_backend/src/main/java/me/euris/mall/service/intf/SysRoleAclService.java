package me.euris.mall.service.intf;

import me.euris.mall.model.vo.ResponseVO;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 06:25:00
 */

public interface SysRoleAclService {
    ResponseVO<String> create();
    ResponseVO<String> update();
    ResponseVO<String> delete();
}
