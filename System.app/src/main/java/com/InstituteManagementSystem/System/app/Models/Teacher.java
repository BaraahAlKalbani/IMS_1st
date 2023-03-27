package com.InstituteManagementSystem.System.app.Models;

/**
 * The Teacher class represents a teacher in an institute management system.
 */
public class Teacher {

    // The ID of the teacher.
    public int id;
    // The salary of the teacher.
    public Double salary;
    // The name of the teacher.
    public String name;
    // The email address of the teacher.
    public String email;

    public Teacher(String name, String email, double salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
