package me.euris.mall.dao;

import me.euris.mall.model.domain.SysRole;

public interface SysRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole row);

    int insertSelective(SysRole row);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole row);

    int updateByPrimaryKey(SysRole row);
}