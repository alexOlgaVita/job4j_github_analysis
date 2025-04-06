package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.model.Commit;

import java.util.List;
import java.util.Optional;

public interface CommitRepository extends CrudRepository<Commit, Long> {

    @Query("from Commit")
    List<Commit> findAll();

    @Query("from Commit comm WHERE comm.repository in (select repo from Repository as repo WHERE repo.name = :name) order by comm.date DESC limit 1")
    Optional<Commit> findLastCommit(@Param("name") String repoName);
}