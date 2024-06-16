package cn.edu.tyut.connectx.auth.infra.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 角色表(AuthRole)实体类
 *
 * @author makejava
 * @since 2024-06-16 14:49:29
 */
@Data
public class AuthRole implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 唯一标识
     */
    private String roleKey;
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
