package com.application.EmployeeApp.service;

import com.application.EmployeeApp.entity.Employees;
import com.application.EmployeeApp.entity.Salary;
import com.application.EmployeeApp.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {

    private final double TAX_RATE = 0.2; // Primjer porezne stope

    @Autowired
    private SalaryRepository salaryRepository;

    public List<Salary> findAllSalaries() {
        return salaryRepository.findAll();
    }

    public Salary findSalaryById(Long id) {
        Salary salary = salaryRepository.findById(id).orElseThrow(() -> new RuntimeException("No salary specified."));
        return  salary;
    }

    public void createSalary (Salary salary) {
        salaryRepository.save(salary);
    }

    public void deleteSalary(Long id) {
        Salary salary = salaryRepository.findById(id).orElseThrow(() -> new RuntimeException("No salary specified."));
        salaryRepository.deleteById(salary.getId());
    }

    public void updateSalary(Salary salary) {
        salaryRepository.save(salary);
    }

    public double calculateNettoSalary(double bruttoSalary) {
        double nettoSalary = bruttoSalary - (bruttoSalary * TAX_RATE);
        return nettoSalary;
    }

    public double calculateBruttoSalary(double nettoSalary) {
        double bruttoSalary = nettoSalary / (1 - TAX_RATE);
        return bruttoSalary;
    }

}
