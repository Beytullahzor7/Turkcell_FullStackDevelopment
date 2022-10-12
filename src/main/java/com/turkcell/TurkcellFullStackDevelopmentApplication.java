package com.turkcell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})

//Auditing
@EnableJpaAuditing(auditorAwareRef = "auditorAwareMethod")
public class TurkcellFullStackDevelopmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurkcellFullStackDevelopmentApplication.class, args);
    }
}
