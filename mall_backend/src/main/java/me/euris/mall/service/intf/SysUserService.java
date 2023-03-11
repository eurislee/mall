package me.euris.mall.service.intf;

import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.model.vo.SysUserVO;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 02:39:00
 */
public interface SysUserService {
    ResponseVO<String> create(SysUserVO vo);
    ResponseVO<String> update(SysUserVO vo);
    ResponseVO<String> delete(Long id);

}
