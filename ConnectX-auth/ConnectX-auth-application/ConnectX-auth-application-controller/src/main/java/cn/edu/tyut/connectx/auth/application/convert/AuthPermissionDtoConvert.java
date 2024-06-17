package cn.edu.tyut.connectx.auth.application.convert;

import cn.edu.tyut.connectx.auth.application.dto.AuthPermissionDto;
import cn.edu.tyut.connectx.auth.domain.entity.AuthPermissionBo;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/17
 */
@Mapper(componentModel = "spring")
public interface AuthPermissionDtoConvert {
    /**
     * dto to bo
     *
     * @param authPermissionDto dto
     * @return bo
     */
    AuthPermissionBo convertAuthPermissionDtoToAuthPermissionBo(AuthPermissionDto authPermissionDto);
}
