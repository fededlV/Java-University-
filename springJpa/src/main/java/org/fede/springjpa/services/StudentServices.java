package org.fede.springjpa.services;

import org.fede.springjpa.dto.StudentDto;
import org.fede.springjpa.dto.StudentResponseDto;
import org.fede.springjpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServices {
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentServices(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    public StudentResponseDto saveStudent(
            StudentDto dto
    ) {
        var student = studentMapper.toStudent(dto);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResposeDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResposeDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findStudentById(
            Integer id
    ) {
        return studentRepository.findById(id)
                .map(studentMapper::toStudentResposeDto)
                .orElse(null);
    }

    public List<StudentResponseDto> findStudentsByName(
            String name
    ) {
        return studentRepository.findByFirstnameContaining(name)
                .stream()
                .map(studentMapper::toStudentResposeDto)
                .collect(Collectors.toList());
    }

    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

}
