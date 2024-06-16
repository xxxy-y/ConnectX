package cn.edu.tyut.connectx.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色Bo
 *
 * @author makejava
 * @since 2024-06-16 14:49:29
 */
@Data
public class AuthRoleBo implements Serializable {
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
}
