package cn.edu.tyut.connectx.auth.common.enums;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * 用户状态枚举
 *
 * @author wqt19
 */
@Getter
public enum AuthUserStatusEnum {
    // 0 未删除   1 已删除
    OPEN(0, "启用"), CLOSE(1, "禁用");
    private final int code;
    private final String desc;

    @Contract(pure = true)
    AuthUserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static @Nullable AuthUserStatusEnum getByCode(int code) {
        for (AuthUserStatusEnum isDeletedFlagEnum : AuthUserStatusEnum.values()) {
            if (isDeletedFlagEnum.getCode() == code) {
                return isDeletedFlagEnum;
            }
        }
        return null;
    }
}
