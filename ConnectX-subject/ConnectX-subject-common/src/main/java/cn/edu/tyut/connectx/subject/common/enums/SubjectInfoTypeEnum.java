package cn.edu.tyut.connectx.subject.common.enums;

import lombok.Getter;

/**
 * 题目类型枚举
 * 1 单选 2 多选 3 判断 4 解答
 *
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
@Getter
public enum SubjectInfoTypeEnum {
    // 1 单选 2 多选 3 判断 4 解答
    RADIO(1, "单选"),
    MULTIPLE(2, "多选"),
    JUDGE(3, "判断"),
    BRIEF(4, "简答");

    private final int code;
    private final String desc;
    SubjectInfoTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectInfoTypeEnum getByCode(int code) {
        for (SubjectInfoTypeEnum item : SubjectInfoTypeEnum.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
