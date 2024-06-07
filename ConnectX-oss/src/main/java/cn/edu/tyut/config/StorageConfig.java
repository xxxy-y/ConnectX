package cn.edu.tyut.config;

import cn.edu.tyut.adapter.AliStorageAdapter;
import cn.edu.tyut.adapter.MinioStorageAdapter;
import cn.edu.tyut.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/7
 */
@Configuration
public class StorageConfig {
    @Value("${storage.service.type}")
    private String type;

    @Bean
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
