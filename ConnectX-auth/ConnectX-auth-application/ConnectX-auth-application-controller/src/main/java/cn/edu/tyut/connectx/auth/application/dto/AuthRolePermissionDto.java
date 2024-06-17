package cn.edu.tyut.connectx.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色权限关系表(AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2024-06-17 14:52:07
 */
@Data
public class AuthRolePermissionDto implements Serializable {
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
    /**
     * 权限id列表
     */
    private List<Long> permissionIdList;
}
