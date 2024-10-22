package org.fede.springjpa.repositories;

import org.fede.springjpa.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByFirstnameContaining(String n);
}
