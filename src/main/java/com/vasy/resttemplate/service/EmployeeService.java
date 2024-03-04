package com.vasy.resttemplate.service;

import com.vasy.resttemplate.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    Employee deleteEmployeeByID(Long id);

}
