package com.application.EmployeeApp.repository;

import com.application.EmployeeApp.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository <Employees, Long> {
}
