package cn.edu.tyut.connectx.auth.domain.service;

import cn.edu.tyut.connectx.auth.domain.entity.AuthPermissionBo;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/17
 */
public interface AuthPermissionDomainService {
    /**
     * 新增权限
     *
     * @param authPermissionBo 新增权限信息
     * @return 返回是否增加成功
     */
    Boolean add(AuthPermissionBo authPermissionBo);

    /**
     * 更新权限
     *
     * @param authPermissionBo 权限信息
     * @return 结果
     */
    Boolean update(AuthPermissionBo authPermissionBo);

    /**
     * 删除权限
     *
     * @param authPermissionBo 权限信息
     * @return 结果
     */
    Boolean delete(AuthPermissionBo authPermissionBo);

    /**
     * 查询用户权限
     *
     * @param userName 用户名称
     * @return 返回查询到的用户权限
     */
    List<String> getPermission(String userName);
}
