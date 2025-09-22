package com.example.StudentManagementSite.services;

import com.example.StudentManagementSite.entity.Student;
import com.example.StudentManagementSite.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{


    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> find(String substring) {
        return studentRepository.findStudents(substring);
    }


}
