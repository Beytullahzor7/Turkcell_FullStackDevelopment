package com.turkcell.bean;

import com.turkcell.audit.AuditorAwareImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorAwareBean {

    // Dikkat: auditorAwareMethod ==>
    // @SpringBootApplication ==> @EnableJpaAuditing(auditorAwareRef = "auditorAwareMethod")
    @Bean
    public AuditorAware auditorAwareMethod() {
        return new AuditorAwareImp();
    }
}
