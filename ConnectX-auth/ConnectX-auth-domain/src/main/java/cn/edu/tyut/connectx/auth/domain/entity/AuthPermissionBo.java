package cn.edu.tyut.connectx.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限DTO
 *
 * @author makejava
 * @since 2024-06-17 10:10:33
 */
@Data
public class AuthPermissionBo implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 权限类型 0 菜单 1 操作
     */
    private Integer type;
    /**
     * 菜单路由
     */
    private String menuUrl;
    /**
     * 状态 0启用 1禁用
     */
    private Integer status;
    /**
     * 展示状态 0 展示 1隐藏
     */
    private Integer show;
    /**
     * 图标
     */
    private String icon;
    /**
     * 权限唯一标识
     */
    private String permissionKey;
}
