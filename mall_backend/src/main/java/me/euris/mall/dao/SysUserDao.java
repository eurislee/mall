package me.euris.mall.dao;

import me.euris.mall.model.domain.SysUser;

import java.util.List;

public interface SysUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser row);

    int insertSelective(SysUser row);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser row);

    int updateByPrimaryKey(SysUser row);

    int selectByCellphone(Long id, String cellphone);
    int selectByEmail(Long id, String mail);
    List<SysUser> selectByIdList(List<Long> idList);
}