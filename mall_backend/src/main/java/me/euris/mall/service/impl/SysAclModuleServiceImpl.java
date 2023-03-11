package me.euris.mall.service.impl;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import me.euris.mall.dao.SysAclModuleDao;
import me.euris.mall.exception.ParamException;
import me.euris.mall.model.domain.SysAclModule;
import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.model.vo.SysAclModuleVO;
import me.euris.mall.service.intf.SysAclModuleService;
import me.euris.mall.util.LevelUtil;
import me.euris.mall.util.ValidatorUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 05:10:00
 */

@Service
@Slf4j
public class SysAclModuleServiceImpl implements SysAclModuleService {

    @Autowired
    private SysAclModuleDao sysAclModuleDao;

    /**
     * @Name create
     * @Description 创建权限模块
     * @Author Euris Lee
     * @param: vo 权限模块信息
     * @CreateTime 3/11/23 6:54 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> create(SysAclModuleVO vo) {
        ValidatorUtil.check(vo);
        int resultCount = sysAclModuleDao.selectByNameAndParentId(vo.getParentId(), vo.getName(), vo.getId());
        if (resultCount > 0) {
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }

        SysAclModule aclModule = SysAclModule.builder().parentId(vo.getParentId()).name(vo.getName())
                .rank(vo.getRank()).status(vo.getStatus()).remark(vo.getRemark()).build();

        aclModule.setLevel(LevelUtil.calculateLevel(getLevel(vo.getParentId()), vo.getParentId()));
        aclModule.setOperator("system");
        aclModule.setOperateIp("127.0.0.1");
        sysAclModuleDao.insertSelective(aclModule);

        return ResponseVO.success("权限模块创建成功");
    }

    /**
     * @Name update
     * @Description 更新权限模块
     * @Author Euris Lee
     * @param: vo 权限模块信息
     * @CreateTime 3/11/23 6:53 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> update(SysAclModuleVO vo) {
        ValidatorUtil.check(vo);
        int resultCount = sysAclModuleDao.selectByNameAndParentId(vo.getParentId(), vo.getName(), vo.getId());
        if (resultCount > 0) {
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }

        SysAclModule history = sysAclModuleDao.selectByPrimaryKey(vo.getId());
        Preconditions.checkNotNull(history, "待更新的权限模块不存在");

        SysAclModule current = SysAclModule.builder().id(vo.getId()).parentId(vo.getParentId()).name(vo.getName())
                .rank(vo.getRank()).status(vo.getStatus()).remark(vo.getRemark()).build();

        current.setLevel(LevelUtil.calculateLevel(getLevel(vo.getParentId()), vo.getParentId()));
        current.setOperator("system");
        current.setOperateIp("127.0.0.1");
        current.setUpdateTime(LocalDateTime.now());

        updateWithChildren(history, current);
        return ResponseVO.success("权限模块更新成功");
    }

    /**
     * @Name delete
     * @Description 删除权限模块
     * @Author Euris Lee
     * @param: id 权限模块id
     * @CreateTime 3/11/23 6:53 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> delete(Long id) {
        SysAclModule aclModule = sysAclModuleDao.selectByPrimaryKey(id);
        Preconditions.checkNotNull(aclModule, "待删除的权限模块不存在，无法删除");
        int resultCount = sysAclModuleDao.selectByParentId(id);
        if (resultCount > 0) {
            throw new ParamException("当前模块下面有子模块，无法删除");
        }

        sysAclModuleDao.deleteByPrimaryKey(id);
        return ResponseVO.success();
    }

    /**
     * @Name getLevel
     * @Description 获取当前权限模块的层级
     * @Author Euris Lee
     * @param: id
     * @CreateTime 3/11/23 6:56 AM
     * @return: java.lang.String
     * @Throws
     */
    private String getLevel(Long id) {
        SysAclModule aclModule = sysAclModuleDao.selectByPrimaryKey(id);
        if (aclModule == null) {
            return null;
        }
        return aclModule.getLevel();
    }

    /**
     * @Name updateWithChildren
     * @Description 批量更新权限模块和权限模块子模块的层级
     * @Author Euris Lee
     * @param: history
     * @param: current
     * @CreateTime 3/11/23 6:54 AM
     * @Throws
     */
    @Transactional
    public void updateWithChildren(SysAclModule history, SysAclModule current) {
        String currentLevelPrefix = current.getLevel();
        String historyLevelPrefix = history.getLevel();
        if (!current.getLevel().equals(history.getLevel())) {
            List<SysAclModule> aclModuleList = sysAclModuleDao.selectChildrenAclModuleListByLevel(history.getLevel());
            if (CollectionUtils.isNotEmpty(aclModuleList)) {
                for (SysAclModule aclModule : aclModuleList) {
                    String level = aclModule.getLevel();
                    if (level.indexOf(historyLevelPrefix) == 0) {
                        level = currentLevelPrefix + level.substring(historyLevelPrefix.length());
                        aclModule.setLevel(level);
                    }
                }
                sysAclModuleDao.batchUpdateLevel(aclModuleList);
            }
        }
        sysAclModuleDao.updateByPrimaryKeySelective(current);
    }
}
