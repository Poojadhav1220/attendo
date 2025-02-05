package com.example.ems.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Integer sr;
    String name;
    LocalDate dob;
    String idDesig;
    Double salary;
    String extension;

    public Employee() {
    }

    public Employee(Long id, Integer sr, String name, LocalDate dob, String idDesig, Double salary, String extension) {
        this.id = id;
        this.sr = sr;
        this.name = name;
        this.dob = dob;
        this.idDesig = idDesig;
        this.salary = salary;
        this.extension = extension;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSr() {
        return sr;
    }

    public void setSr(Integer sr) {
        this.sr = sr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getIdDesig() {
        return idDesig;
    }

    public void setIdDesig(String idDesig) {
        this.idDesig = idDesig;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", sr=" + sr +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", idDesig='" + idDesig + '\'' +
                ", salary=" + salary +
                ", extension='" + extension + '\'' +
                '}';
    }
}
