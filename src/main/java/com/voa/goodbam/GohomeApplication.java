package com.voa.goodbam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@EnableJpaRepositories
public class GohomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(GohomeApplication.class, args);
    }
}
