package com.example.ems.Repository;

import com.example.ems.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Long>
{

   List<Employee> findBySalaryGreaterThan(double sal);
   List<Employee> findBySalary(double sal);
   List<Employee> findByNameStartsWith(String name);
   List<Employee> findByName(String name);
   List<Employee> findByIdDesig(String idDesig);
}
