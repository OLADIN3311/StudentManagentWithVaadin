package com.example.StudentManagementSite.repository;

import com.example.StudentManagementSite.entity.Student;
import com.example.StudentManagementSite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends
        JpaRepository<Student, Integer> {

    // JPQL to find all the students that includes the
    // substring the user defined
    @Query("select s from Student s where lower(s.name) like lower(concat('%', :substring, '%'))")
    List<Student> findStudents(String substring);

    // all the CRUD operations are implemented
}
