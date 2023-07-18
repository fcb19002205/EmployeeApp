package com.application.EmployeeApp.service;

import com.application.EmployeeApp.entity.Employees;
import com.application.EmployeeApp.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeesRepository employeesRepository;


    public List<Employees> findAllEmployees() {
        return employeesRepository.findAll();
    }

    public Employees findEmployeeById(Long id) {
        Employees employee = employeesRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found."));
        return employee;
    }

    public void createEmployee (Employees employee) {
        employeesRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employees employee = employeesRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found."));
        employeesRepository.deleteById(employee.getId());
    }

    public void updateEmployee(Employees employee) {
        employeesRepository.save(employee);
    }

}
