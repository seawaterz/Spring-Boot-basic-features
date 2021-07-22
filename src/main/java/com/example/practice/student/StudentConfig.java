package com.example.practice.student;

import static java.time.Month.JANUARY;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class StudentConfig {
    
    /*Now Applying Beam from spring boot, for that
    declare CommandLineRunner and injecting studentRepo here.
    Also, declaring new student object with properties*/
    @Bean
    CommandLineRunner commandLineRunner(
        StudentRepository repository) {
        return args -> {
			Student maria = new Student(
				"Maria",
				"maria.jamal@gmail.com",
				LocalDate.of(2000, JANUARY, 5)
			);

            Student alex = new Student(
				"Alex",
				"alex@gmail.com",
				LocalDate.of(2004, JANUARY, 5)
			);

            Student mona = new Student(
				"Mona",
				"mona.moni@gmail.com",
				LocalDate.of(2003, JANUARY, 5)
			);

            /**TO save student objects into database here is the process */
            repository.saveAll(
                List.of(maria, alex, mona)
            );
        };
    }
}
