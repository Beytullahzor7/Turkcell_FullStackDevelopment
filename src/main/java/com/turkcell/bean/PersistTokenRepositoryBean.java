package com.turkcell.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
@Configuration
public class PersistTokenRepositoryBean {
    @Autowired
    private DataSource dataSource;

    // Remember Me = Persist Token Repository
    @Bean
    public PersistentTokenRepository persistentTokenRepositoryBeanMethod() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}
