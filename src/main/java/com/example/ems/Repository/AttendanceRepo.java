package com.example.ems.Repository;

import com.example.ems.Entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long>
{

    @Query("from Attendance where idEmp = :idEmp and date = :date")
    Attendance getByIdEmpAndDate(Long idEmp, LocalDate date);

    @Query("from Attendance where idEmp = :idEmp and date = CURRENT_DATE")
    Attendance getByIdEmpAndCurrentDate(Long idEmp);

    @Query("select count(a) from Attendance a where a.idEmp = :idEmp and a.status = :status and function('MONTH', a.date) = function('MONTH', CURRENT_DATE) and function('YEAR', a.date) = function('YEAR', CURRENT_DATE)")
    Integer countStatusCurrentMonth(Long idEmp, int status);
}
