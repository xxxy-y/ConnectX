package cn.edu.tyut.connectx.subject.infra.basic.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目分类关系表(SubjectMapping)实体类
 *
 * @author makejava
 * @since 2024-05-28 14:08:39
 */
@Data
public class SubjectMapping implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 标签id
     */
    private Long labelId;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;
}
