package cn.edu.tyut.connectx.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import cn.edu.tyut.connectx.gateway.entity.AuthPermission;
import cn.edu.tyut.connectx.gateway.entity.AuthRole;
import cn.edu.tyut.connectx.gateway.redis.RedisUtil;
import com.alibaba.fastjson2.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限验证接口扩展
 *
 * @Author 吴庆涛
 * @DATE 2024/6/12
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    private final String authPermissionPrefix = "auth:permission";
    private final String authRolePrefix = "auth:role";
    private RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public List<String> getPermissionList(@NotNull Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        // 1. 在网关处集成ORM框架，直接从数据库查询数据
        // 2. 先从Redis中获取数据，获取不到时走ORM框架查询数据库
        // 3. 先从Redis中获取缓存数据，获取不到时走RPC调用子服务 (专门的权限数据提供服务::auth模块) 获取 ---- 使用这种服务
        return getAuth(authPermissionPrefix, loginId.toString());
    }

    @Override
    public List<String> getRoleList(@NotNull Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        return getAuth(authRolePrefix, loginId.toString());
    }

    private List<String> getAuth(String prefix, String loginId) {
        String authKey = redisUtil.buildKey(prefix, loginId);
        String authValue = redisUtil.get(authKey);
        if (StringUtils.isBlank(authValue)) {
            return Collections.emptyList();
        }
        List<String> authList = new LinkedList<>();
        if (authRolePrefix.equals(prefix)) {
            List<AuthRole> roleList = JSONArray.parseArray(authValue, AuthRole.class);
            authList = roleList.stream().map(AuthRole::getRoleKey).collect(Collectors.toList());
        } else if (authPermissionPrefix.equals(prefix)) {
            List<AuthPermission> permissionList = JSONArray.parseArray(authValue, AuthPermission.class);
            authList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        }
        return authList;
    }
}
