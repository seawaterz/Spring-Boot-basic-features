package com.example.practice.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*This interface class is responsible for data access and 
that is why it is declared with Repository annotation*/
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    /** To implement business logic such as creating a student
     * email does not exist. To do so, a couple of methods are there:
     * 1: run JPQ and 2: apply a method
     */
    //1
     @Query("SELECT s FROM Student s WHERE s.email = ?1")

    //2
     Optional<Student> findStudentByEmail(String email);
}
