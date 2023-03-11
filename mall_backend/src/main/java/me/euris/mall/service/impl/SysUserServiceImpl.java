package me.euris.mall.service.impl;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import me.euris.mall.dao.SysDeptDao;
import me.euris.mall.dao.SysUserDao;
import me.euris.mall.exception.ParamException;
import me.euris.mall.model.domain.SysUser;
import me.euris.mall.model.vo.ResponseVO;
import me.euris.mall.model.vo.SysUserVO;
import me.euris.mall.service.intf.SysUserService;
import me.euris.mall.util.EncryptUtil;
import me.euris.mall.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 02:39:00
 */

@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysDeptDao sysDeptDao;

    /**
     * @Name create
     * @Description 创建用户
     * @Author Euris Lee
     * @param: vo 用户信息
     * @CreateTime 3/11/23 6:50 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> create(SysUserVO vo) {
        ValidatorUtil.check(vo);

        int resultCount = sysUserDao.selectByCellphone(vo.getId(), vo.getCellphone());

        if (resultCount > 0) {
            throw new ParamException("手机号已存在");
        }
        resultCount = sysUserDao.selectByEmail(vo.getId(), vo.getMail());
        if (resultCount > 0) {
            throw new ParamException("邮箱已存在");
        }

        resultCount = sysDeptDao.selectByDeptId(vo.getDeptId());
        if (resultCount == 0) {
            throw new ParamException("部门不存在");
        }

        SysUser user = SysUser.builder().deptId(vo.getDeptId()).username(vo.getUsername()).password(EncryptUtil.MD5EncodeUTF8(vo.getPassword()))
                .mail(vo.getMail()).cellphone(vo.getCellphone()).status(vo.getStatus()).remark(vo.getRemark()).build();

        user.setOperator("system");
        user.setOperateIp("127.0.0.1");

        sysUserDao.insertSelective(user);

        return ResponseVO.success("系统用户创建成功");
    }

    /**
     * @Name update
     * @Description 更新用户
     * @Author Euris Lee
     * @param: vo 用户信息
     * @CreateTime 3/11/23 6:51 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> update(SysUserVO vo) {
        ValidatorUtil.check(vo);

        int resultCount = sysUserDao.selectByCellphone(vo.getId(), vo.getCellphone());

        if (resultCount > 0) {
            throw new ParamException("手机号已存在");
        }
        resultCount = sysUserDao.selectByEmail(vo.getId(), vo.getMail());
        if (resultCount > 0) {
            throw new ParamException("邮箱已存在");
        }
        resultCount = sysDeptDao.selectByDeptId(vo.getDeptId());
        if (resultCount == 0) {
            throw new ParamException("部门不存在");
        }

        SysUser history = sysUserDao.selectByPrimaryKey(vo.getId());
        Preconditions.checkNotNull(history, "待更新的用户不存在");
        SysUser current = SysUser.builder().id(vo.getId()).deptId(vo.getDeptId()).username(vo.getUsername()).cellphone(vo.getCellphone())
                .mail(vo.getMail()).status(vo.getStatus()).remark(vo.getRemark()).build();

        current.setOperator("system");
        current.setOperateIp("127.0.0.1");
        current.setUpdateTime(LocalDateTime.now());

        sysUserDao.updateByPrimaryKeySelective(current);

        return ResponseVO.success();
    }

    /**
     * @Name delete
     * @Description 删除用户
     * @Author Euris Lee
     * @param: id 用户的id
     * @CreateTime 3/11/23 6:51 AM
     * @return: me.euris.mall.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @Override
    public ResponseVO<String> delete(Long id) {
        SysUser user = sysUserDao.selectByPrimaryKey(id);
        Preconditions.checkNotNull(user, "待删除的用户不存在，无法删除");
        sysUserDao.deleteByPrimaryKey(id);
        return ResponseVO.success();
    }
}
