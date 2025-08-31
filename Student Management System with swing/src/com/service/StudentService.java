package com.service;

import com.dao.StudentDao;
import com.model.Students;
import java.util.List;

import java.sql.SQLException;

public class StudentService {
    StudentDao studentDao = new StudentDao();
    public boolean addStudent(Students s) throws SQLException {
        if (s.getName() == null || s.getName().isEmpty()) {
            System.out.println("Name can't be empty");
            return false;
        }
        if (s.getDob().isAfter(s.getAdmissionDate())) {
            System.out.println("Dob can't be after admission date");
            return false;
        }
        if (!s.getEmail().contains("@")) {
            System.out.println("Email should be valid");
        }
        return studentDao.addStudent(s);
    }
    public List<Students> getAllStudent() {
        return studentDao.getAllStudent();
    }
    public Students searchStudent(String rollNo) {
        return studentDao.searchStudent(rollNo);
    }
    public boolean updateStudent(Students s) {
        return studentDao.updateStudent(s);
    }
    public boolean deleteStudent(String rollNo) {
        return studentDao.deleteStudent(rollNo);
    }
}
