package me.euris.mall.service.intf;

import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.model.vo.SysAclModuleVO;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 05:09:00
 */
public interface SysAclModuleService {
    ResponseVO<String> create(SysAclModuleVO vo);
    ResponseVO<String> update(SysAclModuleVO vo);
    ResponseVO<String> delete(Long id);
}
