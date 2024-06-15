package cn.edu.tyut.connectx.auth.common.enums;

import lombok.Getter;

/**
 * 枚举类，用于定义操作结果的状态码和描述信息。
 *
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@Getter
public enum ResultCodeEnum {
    /**
     * SUCCESS 操作成功的状态码
     * FAIL 操作失败的状态码
     */
    SUCCESS(200, "成功"), FAIL(500, "失败");
    private final int code;
    private final String desc;

    ResultCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCodeEnum getResultCodeEnum(int code) {
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if (resultCodeEnum.code == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
