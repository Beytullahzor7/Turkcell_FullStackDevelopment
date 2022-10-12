package com.turkcell.ui.rest;

import com.turkcell.business.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeRest {

    // Save
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    // List
    List<EmployeeDto> getAllEmployees();

    // Find
    ResponseEntity<EmployeeDto> getEmployeeById(Long id) throws Throwable;

    // Update
    ResponseEntity<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto) throws Throwable;

    // Delete
    ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id);
}
