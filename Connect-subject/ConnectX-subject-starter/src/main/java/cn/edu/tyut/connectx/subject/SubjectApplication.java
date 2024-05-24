package cn.edu.tyut.connectx.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 刷题微服务启动类
 *
 * @Author 吴庆涛
 * @DATE 2024/5/23
 */
@SpringBootApplication
@MapperScan("cn.edu.tyut.connectx.**.mapper")
public class SubjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class, args);
    }
}
