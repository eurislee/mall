package me.euris.mall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.euris.mall.model.vo.SysDeptVO;
import me.euris.mall.service.intf.SysDeptService;
import me.euris.mall.util.ValidatorUtil;
import me.euris.mall.model.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-10-2023 19:12:00
 */

@RestController
public class SysDeptController {


    @Autowired
    private SysDeptService sysDeptService;

    /**
     * @Name createSysDept
     * @Description 新增部门
     * @Author Euris Lee
     * @param: vo 部门信息
     * @CreateTime 3/10/23 7:13 PM
     * @return: me.euris.mall.core.model.vo.ResponseVO
     * @Throws
     */
    @PostMapping("/sys/dept/create")
    public ResponseVO<String> createSysDept(SysDeptVO vo) {
        return sysDeptService.create(vo);
    }

    /**
     * @Name deleteSysDept
     * @Description 删除部门
     * @Author Euris Lee
     * @param: id 部门id
     * @CreateTime 3/10/23 11:46 PM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @GetMapping("/sys/dept/delete")
    public ResponseVO<String> deleteSysDept(Long id) {
        return sysDeptService.delete(id);
    }

    /**
     * @Name updateSysDept
     * @Description 更新部门
     * @Author Euris Lee
     * @param: vo 部门信息
     * @CreateTime 3/11/23 1:29 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @PostMapping("/sys/dept/update")
    public ResponseVO<String> updateSysDept(SysDeptVO vo) {
        return sysDeptService.update(vo);
    }

    /**
     * @Name list
     * @Description 部门列表展示
     * @Author Euris Lee
     * @CreateTime 3/11/23 1:30 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.util.List>
     * @Throws
     */
    @GetMapping("/sys/dept/list")
    public ResponseVO<List> list() {
        return sysDeptService.list();
    }
}
