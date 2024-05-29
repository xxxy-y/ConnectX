package cn.edu.tyut.connectx.subject.application.dto;

import lombok.Data;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/28
 */
@Data
public class SubjectAnswerDTO {
    /**
     * 答案选项标识
     */
    private Integer optionType;
    /**
     * 答案
     */
    private String optionContent;
    /**
     * 答案是否正确
     */
    private Integer isCorrect;
}
