package cn.edu.tyut.connectx.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minio配置管理
 * 使用ConfigurationProperties注解来进行属性注入的话，被注入的属性必须有set方法并且名称与配置文件中的名称去掉前缀的名称相同
 *
 * @Author 吴庆涛
 * @DATE 2024/6/6
 */
@Configuration
public class MinioConfig {
    /**
     * minio URL
     */
    @Value("${minio.url}")
    private String url;

    /**
     * minio 账户
     */
    @Value("${minio.access-key}")
    private String accessKey;

    /**
     * minio 密码
     */
    @Value("${minio.secret-key}")
    private String secretKey;

    /**
     * 构造MinioClient
     *
     * @return 返回创建好的MinioClient
     */
    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
    }
}
