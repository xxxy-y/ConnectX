package cn.edu.tyut.connectx.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/30
 */
@Data
public class SubjectOptionBO implements Serializable {
    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;
}
