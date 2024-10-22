package org.fede.springjpa.services;

import org.fede.springjpa.dto.StudentDto;
import org.fede.springjpa.dto.StudentResponseDto;
import org.fede.springjpa.models.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto("Fede",
                "de la Vega",
                "fede@Gmail.com",
                1);

        Student student = mapper.toStudent(dto);
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.gmail(), student.getGmail());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shoul_throw_null_pointer_exception_when_studentDto_is_null() {
        var msg = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The student Dto should not be null", msg.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        //Given
        Student student = new Student(
                "Fede",
                "de la Vega",
                "fede@Gmail.com",
                20
        );
        //When
        StudentResponseDto studentResponseDto = mapper.toStudentResposeDto(student);
        //Then
        assertEquals(studentResponseDto.firstname(), student.getFirstname());
        assertEquals(studentResponseDto.lastname(), student.getLastname());
        assertEquals(studentResponseDto.gmail(), student.getGmail());


    }

}