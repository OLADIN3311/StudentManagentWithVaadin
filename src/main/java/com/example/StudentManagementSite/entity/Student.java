package com.example.StudentManagementSite.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

//import javax.persistence.*;
//import javax.validation.constraints.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotEmpty(message = "Name can not be null")
    private String name;

    @Column
    @Min(value = 0, message = "Age can not be smaller than 0")
    @Max(value = 120, message = "Age can not be larger than 120")
    @NotNull(message = "Age must be specified")
    private int age;

    @Column
    @Min(value = 0, message = "Zip code can not be smaller than 0")
    @Max(value = 9999, message = "Zip code can not be greater than 9999")
    @NotNull(message = "Zip code can not be empty")
    @Digits(integer = 4, fraction = 0, message = "Zip code is a 4 digit integer")
    private int zipCode;

    @Column
    @NotEmpty(message = "Country must be specified")
    private String country;

    @ManyToOne
    @JoinColumn
    private Status status;

    public Student() {

    }

    public Student(String name, int age, int zipCode,
                   String country, Status status) {
        this.name = name;
        this.status = status;
        this.age = age;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
