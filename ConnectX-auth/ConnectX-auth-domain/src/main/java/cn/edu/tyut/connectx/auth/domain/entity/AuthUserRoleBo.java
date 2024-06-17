package cn.edu.tyut.connectx.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色关系表(AuthUserRole)实体类
 *
 * @author makejava
 * @since 2024-06-16 18:06:31
 */
@Data
public class AuthUserRoleBo implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;
}
