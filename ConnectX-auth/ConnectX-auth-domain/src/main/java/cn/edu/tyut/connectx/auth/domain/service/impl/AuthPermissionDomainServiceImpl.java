package cn.edu.tyut.connectx.auth.domain.service.impl;

import cn.edu.tyut.connectx.auth.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.auth.domain.convert.AuthPermissionBoConvert;
import cn.edu.tyut.connectx.auth.domain.entity.AuthPermissionBo;
import cn.edu.tyut.connectx.auth.domain.redis.RedisUtil;
import cn.edu.tyut.connectx.auth.domain.service.AuthPermissionDomainService;
import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthPermission;
import cn.edu.tyut.connectx.auth.infra.basic.service.AuthPermissionService;
import com.alibaba.fastjson2.JSON;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/17
 */
@Service("authPermissionDomainService")
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {
    private static final String AUTH_PERMISSION_PREFIX = "auth:permission";
    private AuthPermissionService authPermissionService;
    private AuthPermissionBoConvert authPermissionBoConvert;
    private RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

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
        authPermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
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
    public Boolean delete(@NotNull AuthPermissionBo authPermissionBo) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBo.getId());
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }

    /**
     * 查询用户权限
     *
     * @param userName 用户名称
     * @return 返回查询到的用户权限
     */
    @Override
    public List<String> getPermission(String userName) {
        String permissionKey = redisUtil.buildKey(AUTH_PERMISSION_PREFIX, userName);
        String permissionValue = redisUtil.get(permissionKey);
        List<AuthPermission> permissionList = JSON.parseArray(permissionValue, AuthPermission.class);
        return permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
    }
}
