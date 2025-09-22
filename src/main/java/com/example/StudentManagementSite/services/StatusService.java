package com.example.StudentManagementSite.services;

import com.example.StudentManagementSite.entity.Status;

import java.util.List;

public interface StatusService {

    public void save(Status status);
    public List<Status> findAll();
}
