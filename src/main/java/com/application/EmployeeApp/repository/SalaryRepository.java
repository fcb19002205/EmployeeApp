package com.application.EmployeeApp.repository;

import com.application.EmployeeApp.entity.Employees;
import com.application.EmployeeApp.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaryRepository extends JpaRepository <Salary, Long> {
}
