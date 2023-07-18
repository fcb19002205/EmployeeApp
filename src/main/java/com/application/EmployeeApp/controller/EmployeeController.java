package com.application.EmployeeApp.controller;

import com.application.EmployeeApp.Form.EmployeeAndSalary;
import com.application.EmployeeApp.entity.Employees;
import com.application.EmployeeApp.entity.Salary;
import com.application.EmployeeApp.service.EmployeeService;
import com.application.EmployeeApp.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/employees")
    public String findAllEmployees(Model model) {
        List<Employees> employees = employeeService.findAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employee/{id}")
    private String findEmployee (@PathVariable Long id, Model model) {
        Employees employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee-list";
    }

    @GetMapping("/remove-employee/{id}")
    public String removeEmployee(@PathVariable Long id, Model model) {
        Employees employee = employeeService.findEmployeeById(id);

        Set<Salary> salaries = employee.getSalaries();
        for (Salary salary : salaries) {
            salaryService.deleteSalary(salary.getId());
        }

        employeeService.deleteEmployee(id);

        model.addAttribute("employees", employeeService.findAllEmployees());
        return "employees";
    }

    @GetMapping("/update-employee/{id}")
    public String updateEmployee(@PathVariable Long id, Model model) {
        Employees employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @PostMapping("/save-update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employees employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-employee";
        }

        Employees existingEmployee = employeeService.findEmployeeById(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setCity(employee.getCity());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());

        employeeService.updateEmployee(existingEmployee);
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("salaries", salaryService.findAllSalaries());
        return "redirect:/employees";
    }

    @GetMapping("/add-employee")
    public String addEmployee(Model model) {
        model.addAttribute("employeeAndSalary", new EmployeeAndSalary());
        return "add-employee";
    }


    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute("employeeAndSalary") EmployeeAndSalary employeeAndSalary, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-employee";
        }

        Employees employee = employeeAndSalary.getEmployee();
        Salary salary = employeeAndSalary.getSalary();
        employee.addSalary(salary);
        salary.addEmployee(employee);
        employeeService.createEmployee(employee);
        salaryService.createSalary(salary);
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("salaries", salaryService.findAllSalaries());
        return "redirect:/employees";
    }

}
