package cn.edu.tyut.connectx.subject.common.enums;

import lombok.Getter;

/**
 * 分类类型枚举
 *
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
@Getter
public enum CategoryTypeEnum {
    // 1 2
    PRIMARY(1, "岗位大类"),
    SECOND(2, "二级分类");

    private final int code;
    private final String desc;

    CategoryTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static CategoryTypeEnum getByCode(int code) {
        for (CategoryTypeEnum e : CategoryTypeEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
