package com.example.StudentManagementSite.services;

import com.example.StudentManagementSite.entity.Student;

import java.util.List;

public interface StudentService {


    public void save(Student student);
    public void remove(Student student);
    public List<Student> findAll();
    public List<Student> find(String substring);

}
