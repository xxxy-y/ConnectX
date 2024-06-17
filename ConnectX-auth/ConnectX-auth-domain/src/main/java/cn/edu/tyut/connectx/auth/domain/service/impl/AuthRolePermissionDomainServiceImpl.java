package cn.edu.tyut.connectx.auth.domain.service.impl;

import cn.edu.tyut.connectx.auth.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.auth.domain.convert.AuthRolePermissionBoConvert;
import cn.edu.tyut.connectx.auth.domain.entity.AuthRolePermissionBo;
import cn.edu.tyut.connectx.auth.domain.service.AuthRolePermissionDomainService;
import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthRolePermission;
import cn.edu.tyut.connectx.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/17
 */
@Service("authRolePermissionDomainService")
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {
    private AuthRolePermissionService authRolePermissionService;
    private AuthRolePermissionBoConvert authRolePermissionBoConvert;

    @Autowired
    public void setAuthRolePermissionBoConvert(AuthRolePermissionBoConvert authRolePermissionBoConvert) {
        this.authRolePermissionBoConvert = authRolePermissionBoConvert;
    }

    @Autowired
    public void setAuthRolePermissionService(AuthRolePermissionService authRolePermissionService) {
        this.authRolePermissionService = authRolePermissionService;
    }

    @Override
    public Boolean add(@NotNull AuthRolePermissionBo authRolePermissionBo) {
        List<AuthRolePermission> authRolePermissionList = new ArrayList<>();
        authRolePermissionBo.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setRoleId(authRolePermissionBo.getRoleId());
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            authRolePermissionList.add(authRolePermission);
        });
        Integer count = authRolePermissionService.insertBatch(authRolePermissionList);
        return count > 0;
    }
}
