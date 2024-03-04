package com.vasy.resttemplate.repo;

import com.vasy.resttemplate.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}