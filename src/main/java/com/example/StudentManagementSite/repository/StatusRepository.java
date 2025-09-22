package com.example.StudentManagementSite.repository;


import com.example.StudentManagementSite.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {


}
