package org.example.domain.repository;

import org.example.domain.model.Repository;

import java.util.List;
import java.util.Set;

public interface RepositoryRepository {

    void saveAll(Set<Repository> repositories);

    List<Repository> findByUser(String user);

    List<Repository> findByLanguage(String language);

    List<Repository> findByTag(String tag);


}
