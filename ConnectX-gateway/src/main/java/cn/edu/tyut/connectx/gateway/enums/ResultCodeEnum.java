package cn.edu.tyut.connectx.gateway.enums;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

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

    @Contract(pure = true)
    ResultCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Contract(pure = true)
    public static @Nullable ResultCodeEnum getResultCodeEnum(int code) {
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if (resultCodeEnum.code == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
