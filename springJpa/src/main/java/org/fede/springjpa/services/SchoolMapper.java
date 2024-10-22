package org.fede.springjpa.services;

import org.fede.springjpa.dto.SchoolDto;
import org.fede.springjpa.models.School;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public SchoolDto toSchoolDto(School school) {
        return new SchoolDto(
                school.getName()
        );
    }

    public School toSchool(SchoolDto schoolDto) {
        var school = new School();
        school.setName(schoolDto.name());
        return school;
    }
}
