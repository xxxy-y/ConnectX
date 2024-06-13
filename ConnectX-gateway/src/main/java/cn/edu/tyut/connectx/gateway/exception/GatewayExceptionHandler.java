package cn.edu.tyut.connectx.gateway.exception;

import cn.dev33.satoken.exception.SaTokenException;
import cn.edu.tyut.connectx.gateway.entity.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关全局异常处理
 *
 * @Author 吴庆涛
 * @DATE 2024/6/13
 */
@Component
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    /**
     * Object Mapper用于将对象转换为JSON格式的字符串
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 覆盖ErrorWebExceptionHandler接口中的handle方法，用于处理Web请求中的异常
     */
    @Override
    public @NotNull Mono<Void> handle(@NotNull ServerWebExchange serverWebExchange, @NotNull Throwable throwable) {
        // 获取ServerWebExchange中的响应对象。
        ServerHttpResponse response = serverWebExchange.getResponse();
        int code;
        String message = "";

        // 判断异常类型，如果是SaTokenException，设置状态码为401，表示用户无权限。
        // 否则，设置状态码为500，表示服务器内部错误。
        if (throwable instanceof SaTokenException) {
            code = 401;
            message = "用户无权限";
        } else {
            code = 500;
            message = "系统繁忙";
        }

        // 创建一个Result对象，封装错误码和错误信息
        Result<Object> result = Result.fail(code, message);

        // 设置响应的内容类型为application/json
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // 返回一个Mono对象，它将写入JSON格式的错误信息
        return response.writeWith(Mono.fromSupplier(() -> {
            // 获取DataBufferFactory实例，用于创建数据缓冲区
            DataBufferFactory bufferFactory = response.bufferFactory();
            byte[] bytes = null;
            try {
                // 将Result对象转换为JSON格式的字节数组
                bytes = objectMapper.writeValueAsBytes(result);
            } catch (JsonProcessingException e) {
                // 如果转换过程中抛出异常，打印堆栈信息
                e.printStackTrace();
            }
            // 断言bytes不为null，确保后续操作有有效的数据
            assert bytes != null;
            // 使用bufferFactory将字节数组包装成DataBuffer对象并返回
            return bufferFactory.wrap(bytes);
        }));
    }
}
