package me.euris.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-09-2023 17:38:00
 */
@SpringBootApplication
@MapperScan(basePackages = "me.euris.mall.dao")
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
