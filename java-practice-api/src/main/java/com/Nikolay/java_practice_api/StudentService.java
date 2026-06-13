package com.Nikolay.java_practice_api;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(s -> new StudentResponse(s.getId(), s.getName(), s.getEmail(), s.getAge()))
                .toList();
    }

    public StudentResponse getStudentById(Long id) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return new StudentResponse(s.getId(), s.getName(), s.getEmail(), s.getAge());
    }

    public StudentResponse addStudent(StudentRequest request) {
        Student s = new Student(null, request.getName(), request.getEmail(), request.getAge());
        Student saved = studentRepository.save(s);
        return new StudentResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getAge());
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        Student updated = new Student(existing.getId(), request.getName(), request.getEmail(), request.getAge());
        Student saved = studentRepository.save(updated);
        return new StudentResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getAge());
    }

}
