package com.application.EmployeeApp.controller;

import com.application.EmployeeApp.entity.Salary;
import com.application.EmployeeApp.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/salary")
    public String findAllSalaries(Model model) {
        List<Salary> salaries = salaryService.findAllSalaries();
        double totalBrutto = salaries.stream().mapToDouble(salary -> Double.parseDouble(salary.getBruttoSalary())).sum();
        double totalNetto = salaries.stream().mapToDouble(salary -> Double.parseDouble(salary.getNettoSalary())).sum();
        model.addAttribute("salaries", salaries);
        model.addAttribute("totalBrutto", totalBrutto);
        model.addAttribute("totalNetto", totalNetto);
        return "salaries";
    }

    @GetMapping("/remove-salary/{id}")
    public String removeSalary(@PathVariable Long id, Model model) {
        salaryService.deleteSalary(id);
        model.addAttribute("salaries", salaryService.findAllSalaries());
        return "salaries";
    }

    @GetMapping("/add-salary")
    public String addSalary(Salary salary, Model model) {
        return "add-salary";
    }

    @PostMapping("/save-salary")
    public String saveSalary(Salary salary, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-salary";
        }
        salaryService.createSalary(salary);
        model.addAttribute("salary", salaryService.findAllSalaries());
        return "redirect:/salary";
    }

    @GetMapping("/update-salary/{id}")
    public String updateSalary(@PathVariable Long id, Model model) {
        Salary salary = salaryService.findSalaryById(id);
        model.addAttribute("salary", salary);
        return "update-salary";
    }

    @PostMapping("/update-salary/{id}")
    public String updateSalary(@PathVariable Long id, Salary salary, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-salary";
        }
        salaryService.updateSalary(salary);
        model.addAttribute("salaries", salaryService.findAllSalaries());
        return "redirect:/salary";
    }

}
