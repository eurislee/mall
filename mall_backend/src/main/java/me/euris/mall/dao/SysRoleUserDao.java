package me.euris.mall.dao;

import me.euris.mall.model.domain.SysRoleUser;

import java.util.List;

public interface SysRoleUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleUser row);

    int insertSelective(SysRoleUser row);

    SysRoleUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleUser row);

    int updateByPrimaryKey(SysRoleUser row);

    List<Long> selectUserIdListByRoleId(Long id);
    List<Long> selectRoleIdListByUserId(Long id);
    List<Long> selectUserIdListByRoleIdList(List<Long> roleIdList);
    int deleteByRoleId(Long id);
    void batchInsert(List<SysRoleUser> roleUserList);

}