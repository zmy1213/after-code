package com.czxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class TosSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TosSpringbootApplication.class, args);
    }

}
