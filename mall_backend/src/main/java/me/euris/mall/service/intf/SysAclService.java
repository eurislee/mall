package me.euris.mall.service.intf;

import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.model.vo.SysAclVO;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 06:07:00
 */
public interface SysAclService {
    ResponseVO<String> create(SysAclVO vo);
    ResponseVO<String> update(SysAclVO vo);
    ResponseVO<String> delete(Long id);
}
