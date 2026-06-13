package com.Nikolay.java_practice_api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getAllStudents_returnsListOfStudentResponses() {
        // arrange
        List<Student> students = List.of(
                new Student(1L, "Alice", "alice@email.com", 20),
                new Student(2L, "Bob", "bob@email.com", 22)
        );
        when(studentRepository.findAll()).thenReturn(students);

        // act
        List<StudentResponse> result = studentService.getAllStudents();

        // assert
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    @Test
    void getStudentById_returnsStudent_whenExists() {
        Student student = new Student(1L, "Alice", "alice@email.com", 20);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentResponse result = studentService.getStudentById(1L);

        assertEquals("Alice", result.getName());
        assertEquals(1L, result.getId());
    }

    @Test
    void getStudentById_throwsException_whenNotFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(99L));
    }

    @Test
    void addStudent_savesAndReturnsStudent() {
        StudentRequest request = new StudentRequest();
        request.setName("Charlie");
        request.setEmail("charlie@email.com");
        request.setAge(21);

        Student saved = new Student(3L, "Charlie", "charlie@email.com", 21);
        when(studentRepository.save(any(Student.class))).thenReturn(saved);

        StudentResponse result = studentService.addStudent(request);

        assertEquals("Charlie", result.getName());
        assertEquals(3L, result.getId());
        verify(studentRepository, times(1)).save(any(Student.class));
    }
}
