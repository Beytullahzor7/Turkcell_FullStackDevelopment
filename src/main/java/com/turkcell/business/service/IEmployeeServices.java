package com.turkcell.business.service;

import com.turkcell.business.dto.EmployeeDto;
import com.turkcell.data.entity.EmployeeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeServices {

    //ModelMapper: Dto ve Entity arasindaki donusumleri gerceklestirdigimiz yapidir
    EmployeeDto entityToDto(EmployeeEntity employeeEntity);

    EmployeeEntity dtoToEntity(EmployeeDto employeeDto);

    //Save
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    //List
    List<EmployeeDto> getAllEmployees();

    //Find
    ResponseEntity<EmployeeDto> getEmployeeById(Long id) throws Throwable;

    //Update
    ResponseEntity<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto) throws Throwable;

    //Delete
    ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id);
}
