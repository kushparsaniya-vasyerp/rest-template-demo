package com.vasy.resttemplate.service.impl;

import com.vasy.resttemplate.models.Employee;
import com.vasy.resttemplate.repo.EmployeeRepository;
import com.vasy.resttemplate.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("employee with id " + id + " not found!")
        );
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = getEmployeeById(id);

        if (isFieldUpdatable(employee.getName(), existingEmployee.getName())) {
            existingEmployee.setName(employee.getName());
        }

        if (isFieldUpdatable(employee.getEmail(), existingEmployee.getEmail())) {
            existingEmployee.setEmail(employee.getEmail());
        }

        return employeeRepository.save(existingEmployee);
    }

    boolean isFieldUpdatable(String newField, String oldField) {
        return newField != null && !newField.isEmpty() && !newField.equals(oldField);
    }

    @Override
    public Employee deleteEmployeeByID(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
