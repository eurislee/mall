package me.euris.mall.dao;

import me.euris.mall.model.domain.SysDept;

import java.util.List;

public interface SysDeptDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysDept row);

    int insertSelective(SysDept row);

    SysDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDept row);

    int updateByPrimaryKey(SysDept row);

    int selectByNameAndParentId(Long id, Long parentId, String deptName);
    int selectByParentId(Long deptId);
    int selectByDeptId(Long id);
    List<SysDept> selectChildrenDeptListByLevel(String level);
    void batchUpdateLevel(List<SysDept> sysDeptList);
    List<SysDept> selectAllDept();
}