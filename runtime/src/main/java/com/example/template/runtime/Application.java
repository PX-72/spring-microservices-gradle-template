package com.example.template.runtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.template")
@EnableJpaRepositories(basePackages = "com.example.template.adapters.out.persistence")
@EntityScan(basePackages = "com.example.template.adapters.out.persistence")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
