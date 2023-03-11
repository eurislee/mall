package me.euris.mall.dao;

import me.euris.mall.model.domain.SysRoleAcl;

public interface SysRoleAclDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleAcl row);

    int insertSelective(SysRoleAcl row);

    SysRoleAcl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleAcl row);

    int updateByPrimaryKey(SysRoleAcl row);
}