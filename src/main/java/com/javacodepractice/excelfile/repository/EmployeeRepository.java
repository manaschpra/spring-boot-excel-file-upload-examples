package com.javacodepractice.excelfile.repository;

import com.javacodepractice.excelfile.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Custom query methods can be defined here if needed
}
