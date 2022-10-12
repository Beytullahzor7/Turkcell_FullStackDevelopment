package com.turkcell;

import com.turkcell.data.entity.EmployeeEntity;
import com.turkcell.data.repository.IEmployeeRepository;
import com.turkcell.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

//Test : Void yazarak devam edilir
//Test : Birbirinden bagimsiz yapilar icin kullaniyoruz
@SpringBootTest
@Log4j2
class TurkcellFullStackDevelopmentApplicationTests implements ITest {

    @Autowired
    IEmployeeRepository repository;

    @BeforeAll
    static void getBeforeAllMethod() {
        System.out.println("Butun Testlerden Once Ben Gelirim");
        log.error("Butun Testlerden Once Ben Gelirim");
    }

    @BeforeEach
    void getPreviousEmployeeBeforeEach() {
        System.out.println("Employee Testten Hemen Oncesi");
    }

    @Test
    @Override
    //Testin Amaci : Bu test db ye veri ekleyebiliyor mu?
    public void testSave() {
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .name("Beytullah")
                .surname("Zor")
                .hesCode("Hes Code 55")
                .price(1200.0)
                .build();

        repository.save(employeeEntity);
        //Test: Eklenen veriyi sistemde gostermeye calisiyoruz
        //Eger eklenmezse yandaki hatayi doner : java,util.NoSuchElementException: No value present
        assertNotNull(repository.findById(6L).get());
    }

    @Test
    @Override
    //Biraz once yazdigimiz testSave i bulabiliyor muyuz?
    public void testFind() {
        EmployeeEntity employeeEntity = repository.findById(6L)
                .orElseThrow(() -> new ResourceNotFoundException("Id could not find"));
        //eğer olmazsa yandaki hatayı döner: java.util.NoSuchElementException: No value present
        //expected: Beklenen
        //actual: Gerçekte olan
        //assertEquals(short expected, short actual)
        //eğer beklenen ile gerçekte olan aynı ise
        // başarılıdır(Success)
        // değilse ==> Failed
        assertEquals("Beytullah", employeeEntity.getName());
    }

    @Test
    @Override
    public void testList() {
        List<EmployeeEntity> entityList = repository.findAll();
        assertThat(entityList).size().isGreaterThan(0);

    }

    @Test
    @Override
    public void testUpdate() {
        EmployeeEntity employeeEntity = repository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Id Bulunamadi"));

        employeeEntity.setName("Updated Name");
        repository.save(employeeEntity);

        assertNotEquals("Beytullah", repository.findById(1L).get().getName());

    }

    @Test
    @Override
    public void testDelete() {
        repository.deleteById(2L);
        assertThat(repository.existsById(2L)).isFalse();
    }

    @AfterEach
    void getPreviousEmployeeAfterEach() {
        System.out.println("Employee Testten Hemen Sonrasinda");
        log.error("Employee Testten hemen sonrasinda");
    }

    @AfterAll
    void getAfterAllMethod() {
        System.out.println("Employee Butun Testlerden Hemen Sonra Gelirim");
        log.error("Employee Butun Testlerden Hemen Sonra Gelirim");
    }
}
