package com.example.StudentManagementSite.services;

public interface SecurityService {

    public void save(String username, String password);
    public void logout();
}
