package cn.edu.tyut.connectx.auth.infra.basic.service;

import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthRole;

import java.util.List;

/**
 * 角色表(AuthRole)表服务接口
 *
 * @author makejava
 * @since 2024-06-16 14:49:30
 */
public interface AuthRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRole queryById(Long id);

    /**
     * 分页查询
     *
     * @param authRole 筛选条件
     */
    void queryByPage(AuthRole authRole);

    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 影响行数
     */
    Integer insert(AuthRole authRole);

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 影响行数
     */
    Integer update(AuthRole authRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据条件查询满足条件的角色
     *
     * @param authRole 条件
     * @return 返回结果
     */
    List<AuthRole> queryByCondition(AuthRole authRole);
}
