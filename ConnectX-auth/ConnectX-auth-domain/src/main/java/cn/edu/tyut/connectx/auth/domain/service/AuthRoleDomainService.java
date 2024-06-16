package cn.edu.tyut.connectx.auth.domain.service;

import cn.edu.tyut.connectx.auth.domain.entity.AuthRoleBo;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/16
 */
public interface AuthRoleDomainService {
    /**
     * 新增角色
     *
     * @param authRoleBo 新增角色信息
     * @return 返回是否新增成功
     */
    Boolean add(AuthRoleBo authRoleBo);

    /**
     * 修改角色
     *
     * @param authRoleBo 角色信息
     * @return 返回是否修改成功
     */
    Boolean update(AuthRoleBo authRoleBo);

    /**
     * 删除角色信息
     *
     * @param authRoleBo 角色信息
     * @return 返回是否删除成功
     */
    Boolean delete(AuthRoleBo authRoleBo);
}
