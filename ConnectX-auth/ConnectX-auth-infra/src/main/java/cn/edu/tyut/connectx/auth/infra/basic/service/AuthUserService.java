package cn.edu.tyut.connectx.auth.infra.basic.service;

import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthUser;

import java.util.List;

/**
 * 用户表(AuthUser)表服务接口
 *
 * @author makejava
 * @since 2024-06-14 21:41:47
 */
public interface AuthUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUser queryById(Long id);

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 影响行数
     */
    Integer insert(AuthUser authUser);

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 影响行数
     */
    Integer update(AuthUser authUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据条件查询数据
     *
     * @param authUser 用户的条件
     * @return 查询出的数据
     */
    List<AuthUser> queryByCondition(AuthUser authUser);
}
