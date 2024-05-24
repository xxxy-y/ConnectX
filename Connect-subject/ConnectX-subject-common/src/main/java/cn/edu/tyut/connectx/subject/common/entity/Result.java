package cn.edu.tyut.connectx.subject.common.entity;

import cn.edu.tyut.connectx.subject.common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;

    /**
     * <T> 是泛型类型，泛型允许你创建类型安全的数据结构，并且可以在运行时提供编译时类型检查。
     *
     * @param <T> 泛型类型
     * @return 结果
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        return result;
    }

    public static <T> Result<T> fail(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        result.setData(data);
        return result;
    }
}
