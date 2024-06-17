package cn.edu.tyut.connectx.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限关系表(AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2024-06-17 14:52:07
 */
@Data
public class AuthRolePermissionBo implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;

    private List<Long> permissionIdList;
}
