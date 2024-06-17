package cn.edu.tyut.connectx.auth.domain.convert;

import cn.edu.tyut.connectx.auth.domain.entity.AuthRolePermissionBo;
import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/17
 */
@Mapper(componentModel = "spring")
public interface AuthRolePermissionBoConvert {
    /**
     * Bo to entity
     * @param authRolePermissionBo bo
     * @return entity
     */
    AuthRolePermission convertAuthRolePermissionBoToAuthRolePermission(AuthRolePermissionBo authRolePermissionBo);
}
