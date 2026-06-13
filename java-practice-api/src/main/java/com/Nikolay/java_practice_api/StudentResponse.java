package com.Nikolay.java_practice_api;

public class StudentResponse {

    private Long id;
    private String name;
    private String email;
    private int age;

    public StudentResponse(Long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
}