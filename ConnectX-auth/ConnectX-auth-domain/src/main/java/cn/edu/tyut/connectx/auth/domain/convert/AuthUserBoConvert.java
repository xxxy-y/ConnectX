package cn.edu.tyut.connectx.auth.domain.convert;

import cn.edu.tyut.connectx.auth.domain.entity.AuthUserBo;
import cn.edu.tyut.connectx.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/14
 */
@Mapper(componentModel = "spring")
public interface AuthUserBoConvert {
    /**
     * BO  TO  entity
     *
     * @param authUserBo BO
     * @return 返回entity
     */
    AuthUser convertAuthUserBoToAuthUser(AuthUserBo authUserBo);

    /**
     * entity to bo
     * @param authUser 用户实体信息
     * @return bo
     */
    AuthUserBo convertAuthUserToAuthUserBo(AuthUser authUser);
}
