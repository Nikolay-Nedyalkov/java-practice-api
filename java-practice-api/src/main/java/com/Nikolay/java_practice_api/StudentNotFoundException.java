package com.Nikolay.java_practice_api;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Student with id " + id + " not found");
    }
}
