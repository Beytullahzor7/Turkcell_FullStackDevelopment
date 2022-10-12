package com.turkcell.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class PasswordEncoderBean {

    //Login-Register icin Maskeleme islemlerinde kullanacagim
    @Bean
    public PasswordEncoder passwordEncoderMethod() {
        return new BCryptPasswordEncoder();
    }
}
