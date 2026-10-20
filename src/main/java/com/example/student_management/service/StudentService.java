
package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setBranch(updatedStudent.getBranch());
        student.setYear(updatedStudent.getYear());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }

        studentRepository.deleteById(id);
    }
}
