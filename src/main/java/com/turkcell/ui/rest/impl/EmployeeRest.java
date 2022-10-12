package com.turkcell.ui.rest.impl;

import com.turkcell.business.dto.EmployeeDto;
import com.turkcell.business.service.IEmployeeServices;
import com.turkcell.ui.rest.IEmployeeRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// http:localhost:8080/api/v1
@RestController
@RequestMapping("/api/v1")
//Rest = Projenin dis dunyaya acilan kapisidir
public class EmployeeRest implements IEmployeeRest {
    IEmployeeServices services;

    public EmployeeRest(IEmployeeServices services) {
        this.services = services;
    }

    // http:localhost:8080/api/v1
    // http:localhost:8080/api/v1/index
    @GetMapping({"/", "/index"})
    public String getRoot() {
        return "index";
    }

    // Save
    // http://localhost:8080/api/v1/employees
    @Override
    @PostMapping("/employees")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        services.createEmployee(employeeDto);
        return employeeDto;
    }

    // List
    // http://localhost:8080/api/v1/employees
    @Override
    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> dtoList = services.getAllEmployees();
        return dtoList;
    }

    // Find
    // http://localhost:8080/api/v1/employees/1
    @Override
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") Long id) throws Throwable {
        ResponseEntity<EmployeeDto> employee = services.getEmployeeById(id);
        return employee;
    }

    // Update
    // http://localhost:8080/api/v1/employees/1
    @Override
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name = "id") Long id, @RequestBody EmployeeDto employeeDto) throws Throwable {
        services.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    // Delete
    // http://localhost:8080/api/v1/employees/1
    @Override
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name = "id") Long id) {
        services.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
