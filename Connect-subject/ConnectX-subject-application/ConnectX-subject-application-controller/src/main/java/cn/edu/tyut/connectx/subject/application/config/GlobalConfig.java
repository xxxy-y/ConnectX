package cn.edu.tyut.connectx.subject.application.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * MVC的全局处理
 *
 * @Author 吴庆涛
 * @DATE 2024/5/31
 */
@Configuration
public class GlobalConfig extends WebMvcConfigurationSupport {
    @Override
    protected void configureMessageConverters(@NotNull List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        // 这个转换器用于支持 JSON 数据的转换
        converters.add(mappingJackson2HttpMessageConverter());
    }

    /**
     * null值(空值)全局处理
     * 就是后端向前端传递的实体类中有很多空的属性，这样前端很难受，所以这样可以将这些取消掉
     *
     * @return 返回一个消息转换器
     */
    @Contract(" -> new")
    private @NotNull MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
}
