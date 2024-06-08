package cn.edu.tyut.connectx.oss.config;

import cn.edu.tyut.connectx.oss.adapter.AliStorageAdapter;
import cn.edu.tyut.connectx.oss.adapter.MinioStorageAdapter;
import cn.edu.tyut.connectx.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RefreshScope注解用来动态修改生成的Bean，当被该注解修饰的方法或者类中的属性或参数被修改时，会自动修改生成的Bean
 *
 * @Author 吴庆涛
 * @DATE 2024/6/7
 */
@Configuration
@RefreshScope
public class StorageConfig {
    @Value("${storage.service.type}")
    private String type;

    @Bean
    @RefreshScope
    public StorageAdapter storageService() {
        if ("minio".equals(type)) {
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(type)) {
            return new AliStorageAdapter();
        } else {
            throw new IllegalArgumentException("未找到对应的文件储存处理器");
        }
    }
}
