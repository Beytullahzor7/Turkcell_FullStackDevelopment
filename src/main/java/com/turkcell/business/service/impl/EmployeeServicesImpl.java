package com.turkcell.business.service.impl;

import com.turkcell.business.dto.EmployeeDto;
import com.turkcell.business.service.IEmployeeServices;
import com.turkcell.data.entity.EmployeeEntity;
import com.turkcell.data.repository.IEmployeeRepository;
import com.turkcell.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Log4j2
@Service
public class EmployeeServicesImpl implements IEmployeeServices {
    @Autowired
    IEmployeeRepository repository;
    @Autowired
    ModelMapper modelMapper;

    // Model Mapper
    @Override
    public EmployeeDto entityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto dto = modelMapper.map(employeeEntity, EmployeeDto.class);
        return dto;
    }

    @Override
    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity entity = modelMapper.map(employeeDto, EmployeeEntity.class);
        return entity;
    }

    // Save
    // http://localhost:8080/save/employees
    @Override
    @PostMapping("/save/employees")
    // PostMapping isleminde @RequestBody anotasyonu kullanmazsak NullPointerException aliriz
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeEntity entity = dtoToEntity(employeeDto);
        repository.save(entity);
        log.info("Employee Veritabanina Kaydedildi");
        return employeeDto;
    }

    // List
    // http://localhost:8080/list/employees
    @Override
    @GetMapping("/list/employees")
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> dtoList = new ArrayList<>();
        Iterable<EmployeeEntity> entities = repository.findAll();
        for (EmployeeEntity entity : entities) {
            EmployeeDto employeeDto = entityToDto(entity);
            dtoList.add(employeeDto);
        }
        return dtoList;
    }

    // Find
    // http://localhost:8080/find/employees/1
    @Override
    @GetMapping("/find/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") Long id) throws Throwable {
        //1. Yol
        EmployeeEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee with " + id + " does not exists!"));

        //2. Yol
//        Optional<EmployeeEntity> employeeEntity = repository.findById(id);
//        if (employeeEntity.isPresent()) {
//            EmployeeEntity entity1 = employeeEntity.get();
//        } else {
//            throw new ResourceNotFoundException("employee with " + id + " does not exists!");
//        }

        EmployeeDto employeeDto = entityToDto(entity);
        return ResponseEntity.ok(employeeDto);
    }

    // Update
    // http://localhost:8080/update/employees/1
    @Override
    @PutMapping("/update/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name = "id") Long id, @RequestBody EmployeeDto employeeDto) throws Throwable {
        //find entity from repository
        EmployeeEntity entityFind = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee with " + id + " does not exists!"));

        //DtoToEntity
        EmployeeEntity entity = dtoToEntity(employeeDto);

        //setEntity
        entityFind.setName(entity.getName());
        entityFind.setSurname(entity.getSurname());
        entityFind.setHesCode(entity.getHesCode());
        entityFind.setPrice(entity.getPrice());

        EmployeeEntity entity1 = repository.save(entityFind);
        EmployeeDto dto = entityToDto(entity1);
        return ResponseEntity.ok(dto);
    }

    // Delete
    // http://localhost:8080/delete/employees/1
    @DeleteMapping("/delete/employees/{id}")
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name = "id") Long id) {
        EmployeeEntity employeeEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee with " + id + " does not exists!"));

        repository.delete(employeeEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Employee Silindi", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
