package cn.edu.tyut.connectx.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * domain 层
 *
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
@Data
public class SubjectLabelBO implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 排序
     */
    private Integer sortNum;
}
