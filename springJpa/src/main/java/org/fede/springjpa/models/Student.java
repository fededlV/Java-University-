package org.fede.springjpa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "T_STUDENT") //specify name of table
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "first_name") //Specify name of column
    private String firstname;
    private String lastname;
    @Column(unique = true) //specify the value of this column is unique
    private String gmail;
    private int age;
    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;
    @ManyToOne
    @JsonBackReference
    private School school;

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Student() {
    }

    public Student(String firstname, String lastname, String gmail, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gmail = gmail;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
