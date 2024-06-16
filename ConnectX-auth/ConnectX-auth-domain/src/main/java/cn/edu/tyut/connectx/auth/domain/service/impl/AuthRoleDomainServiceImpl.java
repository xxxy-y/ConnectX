package cn.edu.tyut.connectx.auth.domain.service.impl;

import cn.edu.tyut.connectx.auth.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.auth.domain.convert.AuthRoleBoConvert;
import cn.edu.tyut.connectx.auth.domain.entity.AuthRoleBo;
import cn.edu.tyut.connectx.auth.domain.service.AuthRoleDomainService;
import cn.edu.tyut.connectx.auth.infra.entity.AuthRole;
import cn.edu.tyut.connectx.auth.infra.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/16
 */
@Service("authRoleDomainService")
@Slf4j
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {
    private AuthRoleService authRoleService;
    private AuthRoleBoConvert authRoleBoConvert;

    @Autowired
    public void setAuthRoleBoConvert(AuthRoleBoConvert authRoleBoConvert) {
        this.authRoleBoConvert = authRoleBoConvert;
    }

    @Autowired
    public void setAuthRoleService(AuthRoleService authRoleService) {
        this.authRoleService = authRoleService;
    }

    /**
     * 新增角色
     *
     * @param authRoleBo 新增角色信息
     * @return 返回是否新增成功
     */
    @Override
    public Boolean add(AuthRoleBo authRoleBo) {
        AuthRole authRole = authRoleBoConvert.convertAuthRoleBoToAuthRole(authRoleBo);
        authRole.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        Integer count = authRoleService.insert(authRole);
        // 这里创建一个角色，不需要与redis做交互
        return count > 0;
    }

    /**
     * 修改角色
     *
     * @param authRoleBo 角色信息
     * @return 是否修改成功
     */
    @Override
    public Boolean update(AuthRoleBo authRoleBo) {
        AuthRole authRole = authRoleBoConvert.convertAuthRoleBoToAuthRole(authRoleBo);
        return authRoleService.update(authRole) > 0;
    }

    /**
     * 删除角色
     *
     * @param authRoleBo 角色信息
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(@NotNull AuthRoleBo authRoleBo) {
        AuthRole authRole = new AuthRole();
        authRole.setId(authRoleBo.getId());
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return authRoleService.update(authRole) > 0;
    }
}
