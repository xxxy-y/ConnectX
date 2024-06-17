package cn.edu.tyut.connectx.auth.domain.service.impl;

import cn.edu.tyut.connectx.auth.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.auth.domain.convert.AuthPermissionBoConvert;
import cn.edu.tyut.connectx.auth.domain.entity.AuthPermissionBo;
import cn.edu.tyut.connectx.auth.domain.service.AuthPermissionDomainService;
import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthPermission;
import cn.edu.tyut.connectx.auth.infra.basic.service.AuthPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/17
 */
@Service("authPermissionDomainService")
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {
    private AuthPermissionService authPermissionService;
    private AuthPermissionBoConvert authPermissionBoConvert;

    @Autowired
    public void setAuthPermissionBoConvert(AuthPermissionBoConvert authPermissionBoConvert) {
        this.authPermissionBoConvert = authPermissionBoConvert;
    }

    @Autowired
    public void setAuthPermissionService(AuthPermissionService authPermissionService) {
        this.authPermissionService = authPermissionService;
    }

    @Override
    public Boolean add(AuthPermissionBo authPermissionBo) {
        AuthPermission authPermission = authPermissionBoConvert.convertAuthPermissionBoToAuthPermission(authPermissionBo);
        authPermission.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        Integer count = authPermissionService.insert(authPermission);
        return count > 0;
    }

    @Override
    public Boolean update(AuthPermissionBo authPermissionBo) {
        AuthPermission authPermission = authPermissionBoConvert.convertAuthPermissionBoToAuthPermission(authPermissionBo);
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBo authPermissionBo) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBo.getId());
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }
}
