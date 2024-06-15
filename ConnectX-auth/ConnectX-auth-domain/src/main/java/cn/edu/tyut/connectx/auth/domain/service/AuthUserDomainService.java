package cn.edu.tyut.connectx.auth.domain.service;

import cn.edu.tyut.connectx.auth.domain.entity.AuthUserBo;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/14
 */
public interface AuthUserDomainService {
    /**
     * 注册用户
     *
     * @param authUserBo 用户信息
     * @return 返回成功与否
     */
    Boolean register(AuthUserBo authUserBo);
}
