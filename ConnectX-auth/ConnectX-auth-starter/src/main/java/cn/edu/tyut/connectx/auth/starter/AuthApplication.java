package cn.edu.tyut.connectx.auth.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/8
 */
@SpringBootApplication
@MapperScan("cn.edu.tyut.**.mapper")
@ComponentScan("cn.edu.tyut")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
