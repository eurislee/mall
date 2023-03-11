package me.euris.mall.dao;

import me.euris.mall.model.domain.SysAcl;

public interface SysAclDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysAcl row);

    int insertSelective(SysAcl row);

    SysAcl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAcl row);

    int updateByPrimaryKey(SysAcl row);

    int selectByNameAndAclModuleId(Long id, String name, Long aclModuleId);
}