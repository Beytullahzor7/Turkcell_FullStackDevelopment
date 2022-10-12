package com.turkcell.bean;

import com.turkcell.data.entity.EmployeeEntity;
import com.turkcell.data.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.UUID;

@Configuration
public class EmployeeEntityBean {
    @Autowired
    IEmployeeRepository repository;
    private int randomData() {
        Random random = new Random();
        int data = random.nextInt(300) + 10;
        return data;
    }

    //Bean Constructordan Once Calisir
    @Bean
    public void employeeAllData() {
        for (int i = 1; i <= 5; i++) {
            UUID uuid = UUID.randomUUID();

            EmployeeEntity employeeEntity = EmployeeEntity.builder()
                    .name("Adi " + randomData())
                    .surname("Surname " + randomData())
                    .hesCode(uuid.toString())
                    .price(randomData() * 100)
                    .build();

            repository.save(employeeEntity);
        }
    }
}
