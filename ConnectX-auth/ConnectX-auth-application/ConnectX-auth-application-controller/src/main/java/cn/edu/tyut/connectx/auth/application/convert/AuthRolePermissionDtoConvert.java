package cn.edu.tyut.connectx.auth.application.convert;

import cn.edu.tyut.connectx.auth.application.dto.AuthRolePermissionDto;
import cn.edu.tyut.connectx.auth.domain.entity.AuthRolePermissionBo;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/17
 */
@Mapper(componentModel = "spring")
public interface AuthRolePermissionDtoConvert {
    /**
     * dto to bo
     *
     * @param authRolePermissionDto 传入的dto
     * @return 返回的bo
     */
    AuthRolePermissionBo convertAuthRolePermissionDtoToAuthRolePermissionBo(AuthRolePermissionDto authRolePermissionDto);
}
