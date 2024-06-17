package cn.edu.tyut.connectx.auth.domain.convert;

import cn.edu.tyut.connectx.auth.domain.entity.AuthPermissionBo;
import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/17
 */
@Mapper(componentModel = "spring")
public interface AuthPermissionBoConvert {
    /**
     * bo to entity
     *
     * @param authPermissionBo bo
     * @return entity
     */
    AuthPermission convertAuthPermissionBoToAuthPermission(AuthPermissionBo authPermissionBo);
}
