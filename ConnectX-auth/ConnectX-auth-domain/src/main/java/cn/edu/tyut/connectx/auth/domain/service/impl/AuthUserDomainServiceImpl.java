package cn.edu.tyut.connectx.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.edu.tyut.connectx.auth.common.enums.AuthUserStatusEnum;
import cn.edu.tyut.connectx.auth.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.auth.domain.constants.AuthConstant;
import cn.edu.tyut.connectx.auth.domain.convert.AuthUserBoConvert;
import cn.edu.tyut.connectx.auth.domain.entity.AuthUserBo;
import cn.edu.tyut.connectx.auth.domain.redis.RedisUtil;
import cn.edu.tyut.connectx.auth.domain.service.AuthUserDomainService;
import cn.edu.tyut.connectx.auth.infra.basic.entity.*;
import cn.edu.tyut.connectx.auth.infra.basic.service.AuthRoleService;
import cn.edu.tyut.connectx.auth.infra.basic.service.AuthUserRoleService;
import cn.edu.tyut.connectx.auth.infra.basic.service.AuthUserService;
import cn.edu.tyut.connectx.auth.infra.basic.service.impl.AuthPermissionServiceImpl;
import cn.edu.tyut.connectx.auth.infra.basic.service.impl.AuthRolePermissionServiceImpl;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/14
 */
@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    private static final String AUTH_PERMISSION_PREFIX = "auth:permission";
    private static final String AUTH_ROLE_PREFIX = "auth:role";
    private static final String LOGIN_PREFIX = "loginCode";
    private AuthUserService authUserService;
    private AuthUserBoConvert authUserBoConvert;
    private AuthRoleService authRoleService;
    private AuthUserRoleService authUserRoleService;
    private RedisUtil redisUtil;
    private AuthRolePermissionServiceImpl authRolePermissionService;
    private AuthPermissionServiceImpl authPermissionService;

    @Autowired
    public void setAuthPermissionService(AuthPermissionServiceImpl authPermissionService) {
        this.authPermissionService = authPermissionService;
    }

    @Autowired
    public void setAuthRolePermissionService(AuthRolePermissionServiceImpl authRolePermissionService) {
        this.authRolePermissionService = authRolePermissionService;
    }

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Autowired
    public void setAuthUserService(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Autowired
    public void setAuthUserBoConvert(AuthUserBoConvert authUserBoConvert) {
        this.authUserBoConvert = authUserBoConvert;
    }

    @Autowired
    public void setAuthRoleService(AuthRoleService authRoleService) {
        this.authRoleService = authRoleService;
    }

    @Autowired
    public void setAuthUserRoleService(AuthUserRoleService authUserRoleService) {
        this.authUserRoleService = authUserRoleService;
    }

    /**
     * 注册用户
     *
     * @param authUserBo 用户信息
     * @return 返回成功与否
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean register(AuthUserBo authUserBo) {
        AuthUser authUser = authUserBoConvert.convertAuthUserBoToAuthUser(authUserBo);
        if (!StringUtils.isBlank(authUser.getPassword())) {
            authUser.setPassword(SaSecureUtil.md5(authUser.getPassword()));
        }
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        // 建立一个初步的角色的关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        List<AuthRole> authRoles = authRoleService.queryByCondition(authRole);
        Long authRoleId = authRoles.get(0).getId();
        Long authUserId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRoleId(authRoleId);
        authUserRole.setUserId(authUserId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count1 = authUserRoleService.insert(authUserRole);

        // 要把当前用户的角色和权限都刷到我们的redis中
        // 将当前用户的角色刷到我们的redis中，可能一个用户有多个角色
        String roleKey = redisUtil.buildKey(AUTH_ROLE_PREFIX, authUser.getUserName());
        List<AuthRole> roleList = new LinkedList<>();
        roleList.add(authRole);
        // 将一个用户的角色信息存入redis  auth:role:username  normal_user
        redisUtil.set(roleKey, JSON.toJSONString(roleList));

        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(authRoleId);
        // 获得多条角色权限关系表
        List<AuthRolePermission> authRolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);

        List<Long> permissionIdList = authRolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
        // 根据permission id查询权限信息
        List<AuthPermission> authPermissionList = authPermissionService.queryByPermissionList(permissionIdList);
        String permissionKey = redisUtil.buildKey(AUTH_PERMISSION_PREFIX, authUser.getUserName());
        redisUtil.set(permissionKey, JSON.toJSONString(authPermissionList));
        return count > 0 && count1 > 0;
    }

    /**
     * 更新用户信息
     *
     * @param authUserBo 用户信息
     * @return 返回是否更新成功
     */
    @Override
    public Boolean update(AuthUserBo authUserBo) {
        AuthUser authUser = authUserBoConvert.convertAuthUserBoToAuthUser(authUserBo);
        Integer count = authUserService.update(authUser);
        // 有任何的更新都要与缓存进行同步的修改
        return count > 0;
    }

    /**
     * 删除用户
     *
     * @param authUserBo 用户信息
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(@NotNull AuthUserBo authUserBo) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBo.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        // 有任何的更新都要与缓存进行同步的修改
        return count > 0;
    }

    @Override
    public Boolean changeStatus(@NotNull AuthUserBo authUserBo) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBo.getId());
        authUser.setStatus(authUserBo.getStatus());
        Integer count = authUserService.update(authUser);
        return count > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaTokenInfo doLogin(String validCode) {
        String loginKey = redisUtil.buildKey(LOGIN_PREFIX, validCode);
        String openId = redisUtil.get(loginKey);
        if (StringUtils.isBlank(openId)) {
            log.error("AuthUserDomainServiceImpl:doLogin: ----> openId为空");
            return null;
        }
        AuthUserBo authUserBo = new AuthUserBo();
        authUserBo.setUserName(openId);
        Boolean flag = register(authUserBo);
        if (flag) {
            StpUtil.login(openId);
            return StpUtil.getTokenInfo();
        }
        log.info("AuthUserDomainServiceImpl:doLogin: ----> 用户注册失败");
        return null;
    }
}
