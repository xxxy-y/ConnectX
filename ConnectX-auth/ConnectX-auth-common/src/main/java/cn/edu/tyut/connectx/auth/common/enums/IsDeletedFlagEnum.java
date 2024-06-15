package cn.edu.tyut.connectx.auth.common.enums;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * 删除状态枚举
 *
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
@Getter
public enum IsDeletedFlagEnum {
    // 0 未删除   1 已删除
    UNDELETED(0, "未删除"), DELETED(1, "已删除");
    private final int code;
    private final String desc;

    @Contract(pure = true)
    IsDeletedFlagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static @Nullable IsDeletedFlagEnum getByCode(int code) {
        for (IsDeletedFlagEnum isDeletedFlagEnum : IsDeletedFlagEnum.values()) {
            if (isDeletedFlagEnum.getCode() == code) {
                return isDeletedFlagEnum;
            }
        }
        return null;
    }
}
