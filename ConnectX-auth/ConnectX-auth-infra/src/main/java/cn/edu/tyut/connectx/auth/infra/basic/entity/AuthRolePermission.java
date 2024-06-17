package cn.edu.tyut.connectx.auth.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 角色权限关系表(AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2024-06-17 14:52:07
 */
@Data
public class AuthRolePermission implements Serializable {
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
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除 0: 未删除 1: 已删除
     */
    private Integer isDeleted;
}
