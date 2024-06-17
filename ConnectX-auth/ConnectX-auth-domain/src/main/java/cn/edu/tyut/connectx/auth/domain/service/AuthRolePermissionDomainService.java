package cn.edu.tyut.connectx.auth.domain.service;

import cn.edu.tyut.connectx.auth.domain.entity.AuthRolePermissionBo;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/17
 */
public interface AuthRolePermissionDomainService {
    /**
     * 新增角色权限关联关系
     *
     * @param authRolePermissionBo 角色权限关联关系
     * @return 结果
     */
    Boolean add(AuthRolePermissionBo authRolePermissionBo);
}
