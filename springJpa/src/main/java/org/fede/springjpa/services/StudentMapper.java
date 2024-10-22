package org.fede.springjpa.services;

import org.fede.springjpa.dto.StudentDto;
import org.fede.springjpa.dto.StudentResponseDto;
import org.fede.springjpa.models.School;
import org.fede.springjpa.models.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
   //Resposible to creating or creating mapping for our student
   public StudentResponseDto toStudentResposeDto(Student student) {
       return new StudentResponseDto(
               student.getFirstname(),
               student.getLastname(),
               student.getGmail()
       );
   }

    public Student toStudent(StudentDto studentDto) {
       if(studentDto == null) {
           throw new NullPointerException("The student Dto should not be null");
       }
        var student = new Student();
        student.setFirstname(studentDto.firstname());
        student.setLastname(studentDto.lastname());
        student.setGmail(studentDto.gmail());
        var school = new School();
        school.setId(studentDto.schoolId());

        student.setSchool(school);

        return student;
    }
}
