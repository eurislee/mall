package me.euris.mall.service.impl;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import me.euris.mall.dao.SysAclDao;
import me.euris.mall.exception.ParamException;
import me.euris.mall.model.domain.SysAcl;
import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.model.vo.SysAclVO;
import me.euris.mall.service.intf.SysAclService;
import me.euris.mall.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 06:07:00
 */

@Service
@Slf4j
public class SysAclServiceImpl implements SysAclService {

    @Autowired
    private SysAclDao sysAclDao;

    /**
     * @Name create
     * @Description 创建权限
     * @Author Euris Lee
     * @param: vo 权限信息
     * @CreateTime 3/11/23 6:51 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> create(SysAclVO vo) {
        ValidatorUtil.check(vo);
        int resultCount = sysAclDao.selectByNameAndAclModuleId(vo.getId(), vo.getName(), vo.getAclModuleId());
        if (resultCount > 0) {
            throw new ParamException("当前权限模块下面存在相同名称的权限");
        }

        SysAcl acl = SysAcl.builder().aclModuleId(vo.getAclModuleId()).name(vo.getName()).url(vo.getUrl())
                .type(vo.getType()).status(vo.getStatus()).rank(vo.getRank()).remark(vo.getRemark()).build();

        acl.setCode(generateCode());
        acl.setOperator("system");
        acl.setOperateIp("127.0.0.1");
        sysAclDao.insertSelective(acl);

        return ResponseVO.success("权限创建成功");
    }

    /**
     * @Name update
     * @Description 更新权限
     * @Author Euris Lee
     * @param: vo 权限信息
     * @CreateTime 3/11/23 6:51 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> update(SysAclVO vo) {
        ValidatorUtil.check(vo);
        int resultCount = sysAclDao.selectByNameAndAclModuleId(vo.getId(), vo.getName(), vo.getAclModuleId());
        if (resultCount > 0) {
            throw new ParamException("当前权限模块下面存在相同名称的权限");
        }

        SysAcl history = sysAclDao.selectByPrimaryKey(vo.getId());
        Preconditions.checkNotNull(history, "待更新的权限不存在");

        SysAcl current = SysAcl.builder().id(vo.getId()).aclModuleId(vo.getAclModuleId()).name(vo.getName())
                .url(vo.getUrl()).type(vo.getType()).status(vo.getStatus()).rank(vo.getRank()).remark(vo.getRemark()).build();

        current.setOperator("system");
        current.setOperateIp("127.0.0.1");
        current.setUpdateTime(LocalDateTime.now());
        sysAclDao.updateByPrimaryKeySelective(current);

        return ResponseVO.success("权限更新成功");
    }

    /**
     * @Name delete
     * @Description 删除权限
     * @Author Euris Lee
     * @param: id 权限的id
     * @CreateTime 3/11/23 6:52 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> delete(Long id) {
        return null;
    }

    /**
     * @Name generateCode
     * @Description 生成权限码
     * @Author Euris Lee
     * @CreateTime 3/11/23 6:52 AM
     * @return: java.lang.String
     * @Throws
     */
    public String generateCode() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return dateTimeFormatter.format(LocalDateTime.now()) + "_" +(int)(Math.random() * 100);
    }
}
