package org.fede.springjpa.controllers;

import jakarta.validation.Valid;
import org.fede.springjpa.dto.StudentDto;
import org.fede.springjpa.dto.StudentResponseDto;
import org.fede.springjpa.services.StudentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @Valid @RequestBody StudentDto dto
    ) {
        return this.studentServices.saveStudent(dto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudents() {
        return this.studentServices.findAllStudents();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto getById(
            @PathVariable("student-id") Integer id
    ) {
        return this.studentServices.findStudentById(id);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> getByName(
            @PathVariable("student-name") String name
    ) {
        return this.studentServices.findStudentsByName(name);
    }

    @DeleteMapping("/student/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("student-id") Integer id
    ) {
        this.studentServices.deleteStudentById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldname = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldname, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
