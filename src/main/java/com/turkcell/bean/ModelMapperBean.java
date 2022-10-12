package com.turkcell.bean;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBean {

    @Bean
    //Entity ve dto arasindaki donusumleri saglamak icin modelMapper kullaniriz
    public ModelMapper modelMapperMethod() {
        //Dikkat: new ModelMapper() yapiyoruz yanlislikla class ismini yazmayalim
        return new ModelMapper();
    }
}
