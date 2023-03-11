package me.euris.mall.service.intf;

import me.euris.mall.model.domain.SysDept;
import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.model.vo.SysDeptVO;

import java.util.List;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-10-2023 19:15:00
 */
public interface SysDeptService {
    ResponseVO<String> create(SysDeptVO vo);
    ResponseVO<String> update(SysDeptVO vo);
    ResponseVO<String> delete(Long id);
    ResponseVO<List> list();
}
