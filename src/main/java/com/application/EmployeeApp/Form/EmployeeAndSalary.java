package com.application.EmployeeApp.Form;

import com.application.EmployeeApp.entity.Employees;
import com.application.EmployeeApp.entity.Salary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeAndSalary {

    private Employees employee;
    private Salary salary;

}
