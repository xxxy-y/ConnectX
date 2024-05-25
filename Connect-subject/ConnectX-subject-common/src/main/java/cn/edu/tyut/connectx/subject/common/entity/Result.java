package cn.edu.tyut.connectx.subject.common.entity;

import cn.edu.tyut.connectx.subject.common.enums.ResultCodeEnum;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

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
     * 第一个 <T> 用来声明类型参数，后面的两个T才是泛型的实现
     * 例子：private <T> T getStudent(List<T> list)
     * 第一个T表示<T>是一个泛型，也就是说T是一个泛型的标志
     * 第二个T表示方法返回的是T类型的数据
     * 第三个T表示集合List传入的数据是T类型
     * 下面的意思是：
     *          第一个<T>表示T是一个泛型，必须在方法上声明
     *          第二个<T>表示方法返回的是Result中传入的数据是T数据
     * @param <T> 泛型类型
     * @return 结果
     */
    public static <T> @NotNull Result<T> ok() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        return result;
    }

    public static <T> @NotNull Result<T> ok(T data) {
        Result<T> result = ok();
        result.setData(data);
        return result;
    }

    public static <T> @NotNull Result<T> fail() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        return result;
    }

    public static <T> @NotNull Result<T> fail(T data) {
        Result<T> result = fail();
        result.setData(data);
        return result;
    }
}
