package com.example.ems.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Attendance
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_sequence")
    @SequenceGenerator(name = "emp_sequence")

    Long id;
    Long idEmp;
    int status; //0=present, 1=absent

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @UpdateTimestamp
    LocalDate date;

    public Attendance() {
    }

    public Attendance(Long id, Long idEmp, int status, LocalDate date) {
        this.id = id;
        this.idEmp = idEmp;
        this.status = status;
        this.date = date;
    }


    public Attendance(long idEmp, int status) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Long idEmp) {
        this.idEmp = idEmp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", idEmp=" + idEmp +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
