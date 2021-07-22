package com.example.practice.student;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Student {

    /*Entity and Table annotation applies here to declare the student class
    as a table in postgres. Generate a sequence by giving name, seqName and alloSize.
    After that GeneratedValues are given such as stragey should be used as sequence and
    generator has a reference to student_sequence. That is all*/
    @Id
    @SequenceGenerator(
        name = "student_Sequence",
        sequenceName =  "student_Sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_Sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    /** This annotation functions as this property does not need
     * need to be stored in database. And the age can be calculated automatically.
     * The age property then can be removed from constructor
     */
    @Transient
    private Integer age;
    
    //constructor having null values
    public Student() {
    }

    //constructor having all values
    public Student(Long id,
                   String name, 
                   String email, 
                   LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    //constructor having values except id as it will be generate by database later on
    public Student(String name, 
                   String email, 
                   LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**Change in this is due to change that has been made
     * using transient annotation to calculate age automatically.
     */
    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", dob='" + getDob() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }


}
