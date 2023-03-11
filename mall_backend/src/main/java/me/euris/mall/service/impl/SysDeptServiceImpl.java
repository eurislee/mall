package me.euris.mall.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import me.euris.mall.dao.SysDeptDao;
import me.euris.mall.exception.ParamException;
import me.euris.mall.model.domain.SysDept;
import me.euris.mall.model.dto.SysDeptDTO;
import me.euris.mall.model.vo.SysDeptVO;
import me.euris.mall.service.intf.SysDeptService;
import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.util.LevelUtil;
import me.euris.mall.util.ValidatorUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-10-2023 19:15:00
 */

@Service
@Slf4j
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptDao sysDeptDao;

    /**
     * @Name create
     * @Description 创建部门
     * @Author Euris Lee
     * @param: vo 部门信息
     * @CreateTime 3/11/23 1:37 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> create(SysDeptVO vo) {
        ValidatorUtil.check(vo);
        int resultCount = sysDeptDao.selectByNameAndParentId(vo.getId(), vo.getParentId(), vo.getName());
        if (resultCount > 0) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }

        SysDept dept = SysDept.builder().name(vo.getName()).parentId(vo.getParentId()).rank(vo.getRank())
                .remark(vo.getRemark()).build();

        dept.setLevel(LevelUtil.calculateLevel(getLevel(vo.getParentId()), vo.getParentId()));
        dept.setOperator("euris");
        dept.setOperateIp("127.0.0.1");

        sysDeptDao.insertSelective(dept);

        return ResponseVO.success("部门创建成功");
    }


    /**
     * @Name update
     * @Description 更新部门
     * @Author Euris Lee
     * @param: vo 部门信息
     * @CreateTime 3/11/23 1:37 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> update(SysDeptVO vo) {
        ValidatorUtil.check(vo);
        int resultCount = sysDeptDao.selectByNameAndParentId(vo.getId(), vo.getParentId(), vo.getName());
        if (resultCount > 0) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept history = sysDeptDao.selectByPrimaryKey(vo.getId());
        Preconditions.checkNotNull(history, "待更新的部门不存在");

        resultCount = sysDeptDao.selectByNameAndParentId(vo.getId(), vo.getParentId(), vo.getName());
        if (resultCount > 0) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }

        SysDept current = SysDept.builder().id(vo.getId()).name(vo.getName()).parentId(vo.getParentId())
                .rank(vo.getRank()).remark(vo.getRemark()).build();

        current.setLevel(LevelUtil.calculateLevel(getLevel(vo.getParentId()), vo.getParentId()));
        current.setOperator("euris");
        current.setOperateIp("127.0.0.1");
        current.setUpdateTime(LocalDateTime.now());

        updateWithChildren(history, current);

        return ResponseVO.success("部门更新成功");
    }

    /**
     * @Name delete
     * @Description 删除部门
     * @Author Euris Lee
     * @param: deptId 部门id
     * @CreateTime 3/11/23 1:37 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> delete(Long id) {
        SysDept dept = sysDeptDao.selectByPrimaryKey(id);
        Preconditions.checkNotNull(dept, "待删除的部门不存在，无法删除");
        int resultCount = sysDeptDao.selectByParentId(dept.getId());
        if (resultCount > 0) {
            throw new ParamException("当前部门下面有子部门，无法删除");
        }

        sysDeptDao.deleteByPrimaryKey(id);
        return ResponseVO.success();
    }

    /**
     * @Name list
     * @Description 返回部门树
     * @Author Euris Lee
     * @CreateTime 3/11/23 1:36 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.util.List>
     * @Throws
     */
    @Override
    public ResponseVO<List> list() {
        List<SysDept> deptList = sysDeptDao.selectAllDept();
        List<SysDeptDTO> dtoList = Lists.newArrayList();
        for (SysDept dept : deptList) {
            SysDeptDTO dto = SysDeptDTO.copyProperties(dept);
            dtoList.add(dto);
        }
        return ResponseVO.success(deptListToTree(dtoList));
    }

    /**
     * @Name deptListToTree
     * @Description 部门List数据结构转换为部门Tree数据结构
     * @Author Euris Lee
     * @param: dtoList
     * @CreateTime 3/11/23 1:34 AM
     * @return: java.util.List<me.euris.mall.model.dto.SysDeptDTO>
     * @Throws
     */
    private List<SysDeptDTO> deptListToTree(List<SysDeptDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }

        Multimap<String, SysDeptDTO> deptMap = ArrayListMultimap.create();
        List<SysDeptDTO> rootList = Lists.newArrayList();

        for (SysDeptDTO dto : dtoList) {
            deptMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        // 按照rank升序排序
        Collections.sort(rootList, deptRankComparator);
        //递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, deptMap);
        return rootList;
    }

    /**
     * @Name transformDeptTree
     * @Description 递归生成树
     * @Author Euris Lee
     * @param: deptList 部门列表
     * @param: level 部门层级
     * @param: deptMap 子部门列表
     * @CreateTime 3/11/23 1:33 AM
     * @Throws
     */
    private void transformDeptTree(List<SysDeptDTO> deptList, String level, Multimap<String, SysDeptDTO> deptMap) {
        for (int i = 0; i < deptList.size(); i++) {
            // 遍历该层的每个元素
            SysDeptDTO dto = deptList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, dto.getId());
            // 处理下一层
            List<SysDeptDTO> nextDeptList = (List<SysDeptDTO>) deptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(nextDeptList)) {
                // 排序
                Collections.sort(nextDeptList, deptRankComparator);
                // 设置下一层部门
                dto.setChildrenDept(nextDeptList);
                // 进入到下一层处理
                transformDeptTree(nextDeptList, nextLevel, deptMap);
            }
        }
    }

    /**
     * @Name updateWithChildren
     * @Description 更新当前部门和所属子部门的信息
     * @Author Euris Lee
     * @param: history 历史值
     * @param: current 当前值
     * @CreateTime 3/11/23 1:32 AM
     * @Throws
     */
    @Transactional
    public void updateWithChildren(SysDept history, SysDept current) {
        String currentLevelPrefix = current.getLevel();
        String historyLevelPrefix = history.getLevel();

        if (!current.getLevel().equals(history.getLevel())) {
            List<SysDept> deptList = sysDeptDao.selectChildrenDeptListByLevel(history.getLevel());
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    if (level.indexOf(historyLevelPrefix) == 0) {
                        level = currentLevelPrefix + level.substring(historyLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                sysDeptDao.batchUpdateLevel(deptList);
            }
        }
        sysDeptDao.updateByPrimaryKeySelective(current);
    }

    /**
     * @Name getLevel
     * @Description 计算当前部门层级
     * @Author Euris Lee
     * @param: deptId 部门id
     * @CreateTime 3/11/23 1:31 AM
     * @return: java.lang.String
     * @Throws
     */
    private String getLevel(Long deptId) {
        SysDept dept = sysDeptDao.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }


    public Comparator<SysDeptDTO> deptRankComparator = new Comparator<SysDeptDTO>() {
        @Override
        public int compare(SysDeptDTO o1, SysDeptDTO o2) {
            return o1.getRank() - o2.getRank();
        }
    };
}
