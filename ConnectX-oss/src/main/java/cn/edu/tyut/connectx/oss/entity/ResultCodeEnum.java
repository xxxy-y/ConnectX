package cn.edu.tyut.connectx.oss.entity;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/24
 */
@Getter
public enum ResultCodeEnum {
    // 成功200， 失败500
    SUCCESS(200,"成功"),
    FAIL(500,"失败");

    public final int code;

    public final String desc;

    @Contract(pure = true)
    ResultCodeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    @Contract(pure = true)
    public static @Nullable ResultCodeEnum getByCode(int codeVal){
        for(ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }
}
