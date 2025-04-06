package ru.job4j.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.job4j.model.Repository;
import ru.job4j.repository.RepoRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class RepositoryService {

    private final RepoRepository repoRepository;

    public RepositoryService(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    @Async
    public void create(Repository repository) {
        repoRepository.save(repository);
    }

    public Collection<Repository> findAll() {
        return (Collection<Repository>) repoRepository.findAll();
    }

    public Optional<Repository> findRepoByName(String name) {
        return repoRepository.findRepoByName(name);
    }
}
