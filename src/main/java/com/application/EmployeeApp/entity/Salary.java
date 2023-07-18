package com.application.EmployeeApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brutto", length = 20, nullable = false)
    private String bruttoSalary;

    @Column(name = "netto", length = 20, nullable = false)
    private String nettoSalary;

    @ManyToMany(mappedBy = "salaries")
    private Set<Employees> employees = new HashSet<>();

    public Set<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employees> employees) {
        this.employees = employees;
    }

    public Salary(String bruttoSalary, String nettoSalary) {
        this.bruttoSalary = bruttoSalary;
        this.nettoSalary = nettoSalary;
        this.employees = new HashSet<>();
    }

    public void addEmployee(Employees employee) {
        this.employees.add(employee);
        employee.getSalaries().add(this);
    }

    public void removeEmployee(Employees employee) {
        this.employees.remove(employee);
        employee.getSalaries().remove(employee);
    }
}

