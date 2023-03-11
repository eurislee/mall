package me.euris.mall.dao;

import me.euris.mall.model.domain.SysAclModule;

import java.util.List;

public interface SysAclModuleDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysAclModule row);

    int insertSelective(SysAclModule row);

    SysAclModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAclModule row);

    int updateByPrimaryKey(SysAclModule row);

    int selectByNameAndParentId(Long parentId, String name, Long id);

    void batchUpdateLevel(List<SysAclModule> sysAclModuleList);
    List<SysAclModule> selectChildrenAclModuleListByLevel(String level);
    int selectByParentId(Long id);
}