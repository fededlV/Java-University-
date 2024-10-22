package org.fede.springjpa.repositories;

import org.fede.springjpa.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
