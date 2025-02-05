package com.example.ems.Controller;

import com.example.ems.Entity.Attendance;
import com.example.ems.Entity.Employee;
import com.example.ems.Repository.AttendanceRepo;
import com.example.ems.Repository.EmpRepo;
import com.example.ems.util.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;

@Controller
public class EmpController
{
    @Autowired
    EmpRepo empRepo;

    @Autowired
    AttendanceRepo attendanceRepo;

    @Autowired
    FileUploader uploader;


   // String[] arrayDesig = {"Developer", "Tester", "HR", "Employee", "Analyst"};

    @GetMapping("/")
    String defaultPage()
    {
        return "redirect:/emp/1/";
    }

    //all employee show
    @GetMapping("/emp/{pageNo}/")
    String showAll(Model model, @PathVariable int pageNo)
    {
        if(pageNo < 1)
        {
            return "redirect:/emp/1/";
        }

        int pageSize = 5;
        Pageable pageable= PageRequest.of(pageNo-1, pageSize, Sort.by("id").descending());

        Page<Employee> page= empRepo.findAll(pageable);
        model.addAttribute("list", page.toList());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNo", pageNo);
        // model.addAttribute("arrayDesig", arrayDesig);
        return "index";
    }

    //specific employee show
    @GetMapping("/emp/find/{id}/")
    Employee showEmp(@PathVariable Long id)
    {
        return empRepo.findById(id).get();
    }

    //add employee
    @PostMapping("/emp/")
    String addEmp(Employee emp, MultipartFile file, Model model)
    {
        String fileNameOld = file.getOriginalFilename();
        String extension= fileNameOld.substring(fileNameOld.indexOf(".") +1 );
        emp.setExtension(extension);
        Employee empSaved = empRepo.save(emp);

        String fileNameNew = empSaved.getId() + "." + extension;

        boolean uploaded = uploader.uploadFile(file, fileNameNew );

//        if(uploaded)
//        {
//            model.addAttribute("Employeee Registration successful");
//        }
//        else
//        {
//            model.addAttribute("Employeee Registration failed");
//
//        }

        return "redirect:/emp/1/";
    }

    //delete employee
    @GetMapping("/emp/delete/{id}/")
    String deleteEmp(@PathVariable Long id)
    {
        Employee emp = empRepo.findById(id).get();
        empRepo.deleteById(id);

        return "redirect:/emp/1/";
    }

    //attendance
    @GetMapping("/attendance/")
    String defaultAttendancePage()
    {
        return "redirect:/attendance/1/";
    }

    @GetMapping("/attendance/{pageNo}/")
    String showAttendance(Model model, @PathVariable int pageNo) {
        if (pageNo < 1) {
            return "redirect:/attendance/1/";
        }

        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("id").descending());

        Page<Employee> page = empRepo.findAll(pageable);
        model.addAttribute("list", page.toList());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNo", pageNo);

        return "attendance";
    }
    @GetMapping("/attendance/mark/{pageNo}/{idEmp}/{status}/")
    @ResponseBody
    public int markAttendance(@PathVariable int pageNo, @PathVariable long idEmp, @PathVariable int status) {

        Optional<Attendance> optionalAttendance = Optional.ofNullable(attendanceRepo.getByIdEmpAndCurrentDate(idEmp));

        Attendance attendance = optionalAttendance.orElse(new Attendance(idEmp, status));
        attendance.setStatus(status);
        attendanceRepo.save(attendance);

        return 1;
    }
//    {
//        int result = 0;
//        Attendance attendance = attendanceRepo.getByIdEmpAndCurrentDate(idEmp);
//
//        if(attendance == null)
//        {
//            attendanceRepo.save(new Attendance(idEmp, status));
//        }
//        else
//        {
//            attendance.setStatus(status);
//            attendanceRepo.save(attendance);
//        }
//
//        result = 1;
//
//        return result;
//    }




}

