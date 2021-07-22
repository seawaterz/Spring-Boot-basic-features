package com.example.practice.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    
    /* Applying dependency injection of studentRepo here at studentService*/
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
		// return List.of(
		// 	new Student(
		// 		1L,
		// 		"Maria",
		// 		"maria.jamal@gmail.com",
		// 		LocalDate.of(2000, Month.JANUARY, 5),
		// 		21
		// 	)
		// );
        return studentRepository.findAll();
	}

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                            .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        studentRepository.save(student);
        // System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.findById(studentId);
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
            
        }
        studentRepository.deleteById(studentId);
        System.out.println("Successfully deleted student data against ID is: " + studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, 
                              String name, 
                              String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                                                    "student with id " + studentId + " does not exist"));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
            
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email is already taken");
            }
            student.setEmail(email);
        }
    }
}
