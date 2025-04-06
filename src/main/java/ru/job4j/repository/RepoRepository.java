package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.model.Repository;

import java.util.Optional;

public interface RepoRepository extends CrudRepository<Repository, Long> {

    @Query("select repo from Repository as repo"
            + " WHERE repo.name = :name"
    )
    Optional<Repository> findRepoByName(@Param("name") String name);
}