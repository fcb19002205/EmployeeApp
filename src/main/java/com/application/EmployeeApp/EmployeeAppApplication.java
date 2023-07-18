package com.application.EmployeeApp;

import com.application.EmployeeApp.entity.Employees;
import com.application.EmployeeApp.entity.Salary;
import com.application.EmployeeApp.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner initialCreate(EmployeeService employeeService) {
//		return args -> {
//			Employees employee2 = new Employees("Gregor", "Blasko", "Stanična 5", "Buje", "gregor@mail.com", "0981666666");
//			Salary salary2 = new Salary("2200.00", "1760.00");
//			employee2.addSalary(salary2);
//			employeeService.createEmployee(employee2);
//
//			Employees employee4 = new Employees("Igor", "Blasko", "Cesta Maršala Tita", "Jesenice", "igor@mail.com", "0981666555");
//			Salary salary4 = new Salary("2000.00", "1600.00");
//			employee4.addSalary(salary4);
//			employeeService.createEmployee(employee4);
//		};
//	}

}
