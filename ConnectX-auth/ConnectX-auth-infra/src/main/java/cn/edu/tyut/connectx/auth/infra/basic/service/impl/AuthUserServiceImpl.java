package cn.edu.tyut.connectx.auth.infra.basic.service.impl;

import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthUser;
import cn.edu.tyut.connectx.auth.infra.basic.mapper.AuthUserDao;
import cn.edu.tyut.connectx.auth.infra.basic.service.AuthUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(AuthUser)表服务实现类
 *
 * @author makejava
 * @since 2024-06-14 21:41:47
 */
@Service("authUserService")
public class AuthUserServiceImpl implements AuthUserService {
    @Resource
    private AuthUserDao authUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthUser queryById(Long id) {
        return this.authUserDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(AuthUser authUser) {
        return this.authUserDao.insert(authUser);
    }

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(AuthUser authUser) {
        return this.authUserDao.update(authUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authUserDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询数据
     *
     * @param authUser 用户的条件
     * @return 返回查询出来的用户数据
     */
    @Override
    public List<AuthUser> queryByCondition(AuthUser authUser) {
        return authUserDao.queryAllByLimit(authUser);
    }
}
