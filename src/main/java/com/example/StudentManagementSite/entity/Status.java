package com.example.StudentManagementSite.entity;

import jakarta.persistence.*;

//import javax.persistence.*;


@Entity
public class Status {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    public Status() {

    }

    public Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
