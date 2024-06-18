package cn.edu.tyut.connectx.auth.infra.basic.service;

import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthPermission;

import java.util.List;

/**
 * 权限表(AuthPermission)表服务接口
 *
 * @author makejava
 * @since 2024-06-17 10:10:34
 */
public interface AuthPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthPermission queryById(Long id);

    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 影响行数
     */
    Integer insert(AuthPermission authPermission);

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    Integer update(AuthPermission authPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 批量查询permission
     *
     * @param permissionIdList permission id
     * @return 返回查询到的权限信息
     */
    List<AuthPermission> queryByPermissionList(List<Long> permissionIdList);
}
