package cn.edu.tyut.connectx.auth.application.convert;

import cn.edu.tyut.connectx.auth.application.dto.AuthUserDto;
import cn.edu.tyut.connectx.auth.domain.entity.AuthUserBo;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/28
 */
@Mapper(componentModel = "spring")
public interface AuthUserDtoConvert {
    /**
     * DTO TO BO
     *
     * @param authUserDto 传入的dto
     * @return 返回的Bo
     */
    AuthUserBo convertAuthUserDtoToAuthUserBo(AuthUserDto authUserDto);
}
