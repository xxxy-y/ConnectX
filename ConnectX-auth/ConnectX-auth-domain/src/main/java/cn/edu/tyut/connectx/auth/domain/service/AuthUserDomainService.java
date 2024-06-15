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

    /**
     * 更新用户信息
     *
     * @param authUserBo 用户信息
     * @return 返回更新结果是否成功
     */
    Boolean update(AuthUserBo authUserBo);

    /**
     * 删除用户信息
     *
     * @param authUserBo 用户信息
     * @return 返回是否删除成功
     */
    Boolean delete(AuthUserBo authUserBo);

    /**
     * 用户启用/禁用状态切换
     *
     * @param authUserBo 用户信息
     * @return 返回是否成功
     */
    Boolean changeStatus(AuthUserBo authUserBo);
}
