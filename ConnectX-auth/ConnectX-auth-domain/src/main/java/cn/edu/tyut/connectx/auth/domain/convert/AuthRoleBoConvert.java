package cn.edu.tyut.connectx.auth.domain.convert;

import cn.edu.tyut.connectx.auth.domain.entity.AuthRoleBo;
import cn.edu.tyut.connectx.auth.infra.entity.AuthRole;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/16
 */
@Mapper(componentModel = "spring")
public interface AuthRoleBoConvert {
    /**
     * 转换
     * @param authRoleBo bo
     * @return entity
     */
    AuthRole convertAuthRoleBoToAuthRole(AuthRoleBo authRoleBo);
}
