package org.fede.springjpa.services;

import org.fede.springjpa.dto.StudentDto;
import org.fede.springjpa.dto.StudentResponseDto;
import org.fede.springjpa.models.Student;
import org.fede.springjpa.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.plugins.MockitoPlugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServicesTest {

    //which service we want to test
    @InjectMocks
    private StudentServices services;

    //declare the dependencies
    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_succesfully_save_a_student() {
        //Given
        StudentDto dto = new StudentDto(
                "fede",
                "de la Vega",
                "fede@Gmial.com",
                1
        );
        Student student = new Student(
                "fede",
                "de la Vega",
                "fede@Gmial.com",
                20
        );
        Student saveStudent = new Student(
                "fede",
                "de la Vega",
                "fede@Gmial.com",
                20
        );
        saveStudent.setId(1);

        // Mock the calls
        when(mapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(saveStudent);
        when(mapper.toStudentResposeDto(saveStudent)).thenReturn(new StudentResponseDto("fede", "de la Vega", "fede@Gmial.com"));

        //When
        StudentResponseDto responseDto = services.saveStudent(dto);

        //Then
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.gmail(), responseDto.gmail());

        verify(mapper, times(1))
                .toStudent(dto);
        verify(repository, times(1))
                .save(student);
        verify(mapper, times(1))
                .toStudentResposeDto(saveStudent);
    }

    @Test
    public void should_return_all_student() {
        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "fede",
                "de la Vega",
                "fede@Gmial.com",
                20
        ));

        //Mock the calls
        when(repository.findAll()).thenReturn(students);
        when(mapper.toStudentResposeDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "fede",
                        "de la Vega",
                        "fede@Gmial.com"
                ));

        //When
        List<StudentResponseDto> responseDtos = services.findAllStudents();

        //Then
        assertEquals(students.size(), responseDtos.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_find_student_by_id(){
        //Given
        Integer studentId = 1;
        Student student = new Student(
                "fede",
                "de la Vega",
                "fede@Gmial.com",
                20
        );

        //Mock the calls
        when(repository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(mapper.toStudentResposeDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "fede",
                        "de la Vega",
                        "fede@Gmial.com"
                ));

        //When
        StudentResponseDto dto = services.findStudentById(studentId);

        //Then
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.gmail(), student.getGmail());

        verify(repository, times(1)).findById(studentId);
    }

    @Test
    public void should_find_student_by_name() {
        //Given
        String name = "fede";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "fede",
                "de la Vega",
                "fede@Gmial.com",
                20
        ));

        //Mock the calls
        when(repository.findByFirstnameContaining(name)).thenReturn(students);
        when(mapper.toStudentResposeDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "fede",
                        "de la Vega",
                        "fede@Gmial.com"
                ));

        //When
        List<StudentResponseDto> responseDtos = services.findStudentsByName(name);

        //Then
        assertEquals(students.size(), responseDtos.size());
        verify(repository, times(1)).findByFirstnameContaining(name);
    }

    @Test
    public void should_delete_student() {
        //Given
        Integer studentId = 1;
        Student student = new Student(
                "fede",
                "de la Vega",
                "fede@Gmial.com",
                20
        );

        //When
        services.deleteStudentById(studentId);

        //Then
        verify(repository, times(1)).deleteById(studentId);


    }
}