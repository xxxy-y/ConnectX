package cn.edu.tyut.connectx.auth.domain.service.impl;

import cn.edu.tyut.connectx.auth.common.enums.AuthUserStatusEnum;
import cn.edu.tyut.connectx.auth.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.auth.domain.convert.AuthUserBoConvert;
import cn.edu.tyut.connectx.auth.domain.entity.AuthUserBo;
import cn.edu.tyut.connectx.auth.domain.service.AuthUserDomainService;
import cn.edu.tyut.connectx.auth.infra.entity.AuthUser;
import cn.edu.tyut.connectx.auth.infra.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/14
 */
@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    private AuthUserService authUserService;
    private AuthUserBoConvert authUserBoConvert;

    @Autowired
    public void setAuthUserService(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Autowired
    public void setAuthUserBoConvert(AuthUserBoConvert authUserBoConvert) {
        this.authUserBoConvert = authUserBoConvert;
    }

    /**
     * 注册用户
     *
     * @param authUserBo 用户信息
     * @return 返回成功与否
     */
    @Override
    public Boolean register(AuthUserBo authUserBo) {
        AuthUser authUser = authUserBoConvert.convertAuthUserBoToAuthUser(authUserBo);
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        Integer count = authUserService.insert(authUser);
        return count > 0;
    }
}
