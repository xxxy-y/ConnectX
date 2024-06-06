package cn.edu.tyut.connectx.subject.infra.basic.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 单选题信息表(SubjectRadio)实体类
 *
 * @author makejava
 * @since 2024-05-29 20:09:14
 */
@Data
public class SubjectRadio implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * a,b,c,d
     */
    private Integer optionType;
    /**
     * 选项内容
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private Integer isCorrect;
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
     * 是否删除 0: 未删除 1: 已删除
     */
    private Integer isDeleted;
}
