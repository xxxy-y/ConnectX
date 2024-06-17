package cn.edu.tyut.connectx.auth.application.convert;

import cn.edu.tyut.connectx.auth.application.dto.AuthRoleDto;
import cn.edu.tyut.connectx.auth.domain.entity.AuthRoleBo;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/16
 */
@Mapper(componentModel = "spring")
public interface AuthRoleDtoConvert {
    /**
     * dto 2 bo
     * @param authRoleDto dto
     * @return bo
     */
    AuthRoleBo convertAuthRoleDtoToAuthRoleBo(AuthRoleDto authRoleDto);
}
