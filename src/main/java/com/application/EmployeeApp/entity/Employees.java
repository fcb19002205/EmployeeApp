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
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "phone", length = 50, nullable = false)
    private String phone;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "employees_salaries", joinColumns = {@JoinColumn(name = "employee_id")}, inverseJoinColumns = {@JoinColumn(name = "salary_id")})
    private Set<Salary> salaries = new HashSet<>();

    public Employees(String name, String lastName, String address, String city, String email, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.salaries = new HashSet<>();
    }

    public void addSalary(Salary salary) {
        this.salaries.add(salary);
        salary.getEmployees().add(this);
    }

    public void removeSalary(Salary salary) {
        this.salaries.remove(salary);
        salary.getEmployees().remove(salary);
    }

}
