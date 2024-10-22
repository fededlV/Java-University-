package org.fede.springjpa.services;

import org.fede.springjpa.dto.SchoolDto;
import org.fede.springjpa.repositories.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServices {

    private final SchoolRepository repository;
    private final SchoolMapper schoolMapper;

    public SchoolServices(SchoolRepository repository, SchoolMapper schoolMapper) {
        this.repository = repository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDto create(SchoolDto dto) {
        var school =  schoolMapper.toSchool(dto);
        repository.save(school);
        return dto;
    }

    public List<SchoolDto> findAll() {
        return repository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }

}
