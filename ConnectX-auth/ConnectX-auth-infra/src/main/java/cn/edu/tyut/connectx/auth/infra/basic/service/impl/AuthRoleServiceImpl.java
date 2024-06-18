package cn.edu.tyut.connectx.auth.infra.basic.service.impl;

import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthRole;
import cn.edu.tyut.connectx.auth.infra.basic.mapper.AuthRoleDao;
import cn.edu.tyut.connectx.auth.infra.basic.service.AuthRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(AuthRole)表服务实现类
 *
 * @author makejava
 * @since 2024-06-16 14:49:31
 */
@Service("authRoleService")
public class AuthRoleServiceImpl implements AuthRoleService {
    @Resource
    private AuthRoleDao authRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthRole queryById(Long id) {
        return this.authRoleDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param authRole 筛选条件
     */
    @Override
    public void queryByPage(AuthRole authRole) {
        long total = this.authRoleDao.count(authRole);
    }

    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 影响行数
     */
    @Override
    public Integer insert(AuthRole authRole) {
        return this.authRoleDao.insert(authRole);
    }

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(AuthRole authRole) {
        return this.authRoleDao.update(authRole);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authRoleDao.deleteById(id) > 0;
    }

    @Override
    public List<AuthRole> queryByCondition(AuthRole authRole) {
        return authRoleDao.queryAllByLimit(authRole);
    }

    @Override
    public List<AuthRole> queryByRoleList(List<Long> roleIdList) {
        return authRoleDao.queryByRoleList(roleIdList);
    }
}
