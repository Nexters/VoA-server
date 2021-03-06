package com.voa.goodbam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@CrossOrigin(origins = "*", allowedHeaders = "*")
@EnableJpaRepositories
@EnableSwagger2
public class GohomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(GohomeApplication.class, args);
    }
}
