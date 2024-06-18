package cn.edu.tyut.connectx.auth.infra.basic.service;

import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthRole;
import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthRolePermission;

import java.util.List;

/**
 * 角色权限关系表(AuthRolePermission)表服务接口
 *
 * @author makejava
 * @since 2024-06-17 14:52:07
 */
public interface AuthRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRolePermission queryById(Long id);

    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 影响行数
     */
    Integer insert(AuthRolePermission authRolePermission);

    /**
     * 批量新增数据
     *
     * @param authRolePermissionList 列表
     * @return 影响行数
     */
    Integer insertBatch(List<AuthRolePermission> authRolePermissionList);

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    AuthRolePermission update(AuthRolePermission authRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据条件查询
     *
     * @param authRolePermission 条件
     * @return 查询结果
     */
    List<AuthRolePermission> queryByCondition(AuthRolePermission authRolePermission);
}
