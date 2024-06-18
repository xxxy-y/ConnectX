package cn.edu.tyut.connectx.auth.infra.basic.service.impl;

import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthRole;
import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthRolePermission;
import cn.edu.tyut.connectx.auth.infra.basic.mapper.AuthRolePermissionDao;
import cn.edu.tyut.connectx.auth.infra.basic.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限关系表(AuthRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2024-06-17 14:52:07
 */
@Service("authRolePermissionService")
public class AuthRolePermissionServiceImpl implements AuthRolePermissionService {
    @Resource
    private AuthRolePermissionDao authRolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthRolePermission queryById(Long id) {
        return this.authRolePermissionDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(AuthRolePermission authRolePermission) {
        return this.authRolePermissionDao.insert(authRolePermission);
    }

    /**
     * 批量新增数据
     *
     * @param authRolePermissionList 列表
     * @return 影响行数
     */
    @Override
    public Integer insertBatch(List<AuthRolePermission> authRolePermissionList) {
        return authRolePermissionDao.insertBatch(authRolePermissionList);
    }

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public AuthRolePermission update(AuthRolePermission authRolePermission) {
        this.authRolePermissionDao.update(authRolePermission);
        return this.queryById(authRolePermission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authRolePermissionDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     *
     * @param authRolePermission 条件
     * @return 查询结果
     */
    @Override
    public List<AuthRolePermission> queryByCondition(AuthRolePermission authRolePermission) {
        return authRolePermissionDao.queryAllByLimit(authRolePermission);
    }
}
