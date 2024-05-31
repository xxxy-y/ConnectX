package cn.edu.tyut.connectx.subject.domain.entity;

import cn.edu.tyut.connectx.subject.common.entity.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息DTO
 *
 * @author makejava
 * @since 2024-05-28 17:34:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SubjectInfoBO extends PageInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3 判断 4 简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 所属分类
     */
    private List<Long> categoryIds;
    /**
     * 所属标签
     */
    private List<Long> labelIds;
    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;

    private Long categoryId;

    private Long labelId;

    /**
     * 所属标签名称
     */
    private List<String> labelName;
}

