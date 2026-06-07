package com.Nikolay.java_practice_api;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>(List.of(
            new Student(1L, "Alice", "alice@email.com", 20),
            new Student(2L, "Bob", "bob@email.com", 22)
    ));

    public List<Student> getAllStudents() {
        return students;
    }

    public Optional<Student> getStudentById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    public void deleteStudent(Long id) {
        students.removeIf(s -> s.getId().equals(id));
    }

    public Optional<Student> updateStudent(Long id, Student updated) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.set(i, new Student(id, updated.getName(), updated.getEmail(), updated.getAge()));
                return Optional.of(students.get(i));
            }
        }
        return Optional.empty();
    }
}
