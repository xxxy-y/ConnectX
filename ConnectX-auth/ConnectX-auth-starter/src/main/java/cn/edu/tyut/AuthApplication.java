package cn.edu.tyut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/8
 */
@SpringBootApplication
@MapperScan("cn.edu.tyut.**.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
